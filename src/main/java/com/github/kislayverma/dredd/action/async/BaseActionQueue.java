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

import com.github.kislayverma.dredd.domain.exception.AsyncTaskConsumptionException;
import com.github.kislayverma.dredd.domain.exception.AsyncTaskSubmissionException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is the default implementation of the async action action queue to which async 
 * action executions are submitted to be processed in FIFO order by an executor consumer.
 * @author kislay.verma
 */
public class BaseActionQueue implements ActionQueue {
    private final Queue<AsyncExecutionRequest> queue;

    public BaseActionQueue() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void submitTask(AsyncExecutionRequest request) throws AsyncTaskSubmissionException {
        if (request != null) {
            queue.offer(request);
        }
    }

    @Override
    public AsyncExecutionRequest getTask() throws AsyncTaskConsumptionException {
        AsyncExecutionRequest task = queue.poll();
        return (task != null) ? task : null;
    }
}
