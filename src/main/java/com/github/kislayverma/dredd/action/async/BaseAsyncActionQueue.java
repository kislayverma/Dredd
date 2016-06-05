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
package com.github.kislayverma.dredd.action.async;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is the default implementation of the async action action queue to which async 
 * action executions are submitted to be processed in FIFO order by an executor consumer.
 * @author kislay.verma
 */
public class BaseAsyncActionQueue implements AsyncActionQueue {
    private final Queue<AsyncExecutionRequest> taskList;

    public BaseAsyncActionQueue() {
        this.taskList = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void submitTask(AsyncExecutionRequest request) {
        if (request != null) {
            taskList.offer(request);
        }
    }

    @Override
    public AsyncExecutionRequest getTask() {
        AsyncExecutionRequest task = taskList.poll();
        return (task != null) ? task : null;
    }
}
