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
package com.github.kislayverma.dredd.domain.action.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a queue to which action execution requests can be submitted for processing in FIFO order.
 * @author kislay.verma
 */
public class AsyncActionQueue {
    private final Queue<AsyncExecutionRequest> taskList = new ConcurrentLinkedQueue<>();
    private final List<Thread> consumerThreadList = new ArrayList<>();
    private static AsyncActionQueue INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncActionQueue.class);

    private AsyncActionQueue() {
        for (int i = 0; i < 50; i++) {
            Thread x = new Thread(new ExecutorThread(), "DR-Thread-" + i);
            x.start();
            consumerThreadList.add(x);
            LOGGER.info("Started thread " + x.getName());
        }
    }

    public static AsyncActionQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsyncActionQueue();
        }

        return INSTANCE;
    }

    public void submit(AsyncExecutionRequest request) {
        if (request != null) {
            taskList.offer(request);
        }
    }

    private class ExecutorThread implements Runnable {
        private void consume() throws InterruptedException {
            while (true) {
                AsyncExecutionRequest task = taskList.poll();
                // Execute the submitted action with its data
                if (task != null) {
                    task.getAction().execute(task.getEntity(), task.getEvent());
                }
            }
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException ex) {
                LOGGER.error("Error processing async action.", ex);
            }
        }
    }
}
