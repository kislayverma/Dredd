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
package com.github.kislayverma.dredd.executor.async;

import com.github.kislayverma.dredd.action.ActionFactory;
import com.github.kislayverma.dredd.action.async.ActionQueue;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the default implementation of consumer for async execution requests. This reads 
 * data from the configured AsycActionQueue, looks up the action, and executes it.
 * 
 * @author kislay.verma
 */
public class BaseAsyncConsumer {
    private final List<Thread> consumerThreadList;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAsyncConsumer.class);

    public BaseAsyncConsumer(ActionQueue queue, ActionFactory actionfactory, int concurrentConsumers) {
        this.consumerThreadList = new ArrayList<>();
        for (int i = 0; i < concurrentConsumers; i++) {
            Thread t = new Thread(
                new BaseAsyncConsumerWorker(queue, actionfactory), "Dredd-Async-Worker-" + i);
            consumerThreadList.add(t);
            t.start();

            LOGGER.info("Started thread " + t.getName());
        }
    }
}
