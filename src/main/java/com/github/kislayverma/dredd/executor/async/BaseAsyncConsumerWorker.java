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
import com.github.kislayverma.dredd.action.async.AsyncActionQueue;
import com.github.kislayverma.dredd.action.async.AsyncExecutionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the default implementation of worker thread for async actions. This reads
 * data from the configured AsycActionQueue and executes the tasks in it.
 *
 * @author kislay.verma
 */
class BaseAsyncConsumerWorker implements Runnable {
    private final AsyncActionQueue taskList;
    private final ActionFactory actionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAsyncConsumerWorker.class);

    public BaseAsyncConsumerWorker(AsyncActionQueue taskList, ActionFactory actionFactory) {
        this.taskList = taskList;
        this.actionFactory = actionFactory;
    }

    private void consume() throws InterruptedException {
        while (true) {
            AsyncExecutionRequest task = taskList.getTask();
            // Execute the submitted action with its data
            if (task != null) {
                actionFactory.getAction(task.getActionType()).execute(task.getEntity(), task.getEvent());
            }
        }
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException ex) {
            LOGGER.error("Error processing async action", ex);
        }
    }
}
