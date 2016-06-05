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

/**
 * This is a queue to which action execution requests can be submitted for asynchronous processing.
 * @author kislay.verma
 */
public interface ActionQueue {

    /**
     * This method submits an asynchronous task to the processing queue.
     * @param request The {@link AsyncExecutionRequest} request to be submitted
     * @throws com.github.kislayverma.dredd.domain.exception.AsyncTaskSubmissionException if the task could not be submitted
     */
    void submitTask(AsyncExecutionRequest request) throws AsyncTaskSubmissionException;

    /**
     * This method returns a task from the queue if present.
     * @return a task from the queue, null if the queue is empty
     * @throws com.github.kislayverma.dredd.domain.exception.AsyncTaskConsumptionException  if a task could not be consumed
     */
    AsyncExecutionRequest getTask() throws AsyncTaskConsumptionException;
}
