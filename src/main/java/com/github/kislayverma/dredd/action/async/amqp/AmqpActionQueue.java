/*
 * Copyright 2016 kislay.verma.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kislayverma.dredd.action.async.amqp;

import com.github.kislayverma.dredd.action.async.ActionQueue;
import com.github.kislayverma.dredd.action.async.AsyncExecutionRequest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an implementation of the action queue based on the AMQP protocol to
 * which async action executions are submitted to be processed in FIFO order by
 * an executor consumer.
 *
 * @author kislay.verma
 */
public class AmqpActionQueue implements ActionQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpActionQueue.class);
    private final ConnectionFactory factory;
    private final String queue;
    private final String exchange;
    private final String routingKey;

    public AmqpActionQueue(ConnectionFactory factory, String queue, String exchange, String routingKey) throws IOException, TimeoutException {
        this.factory = factory;
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchange, "direct", true);
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);
    }

    @Override
    public void submitTask(AsyncExecutionRequest request) {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish(this.exchange, this.routingKey, null, serialize(request));
        } catch (IOException | TimeoutException ex) {
            LOGGER.error("Failed to submit action execution request", ex);
        }
    }

    @Override
    public AsyncExecutionRequest getTask() {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            GetResponse response = channel.basicGet(this.queue, false);
            if (response != null) {
                AsyncExecutionRequest message = deserialize(response.getBody());

                long deliveryTag = response.getEnvelope().getDeliveryTag();
                channel.basicAck(deliveryTag, true);

                return message;
            }
        } catch (IOException | TimeoutException | ClassNotFoundException ex) {
            LOGGER.error("Failed to read message from the queue", ex);
            throw new RuntimeException("Failed to read message from the queue", ex);
        }

        return null; // Won't ever reach here
    }

    private byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try (ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    private AsyncExecutionRequest deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
            try (ObjectInputStream o = new ObjectInputStream(b)) {
                return (AsyncExecutionRequest) o.readObject();
            }
        }
    }
}
