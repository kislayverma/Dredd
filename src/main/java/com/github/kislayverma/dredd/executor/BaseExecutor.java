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
package com.github.kislayverma.dredd.executor;

import com.github.kislayverma.dredd.action.ActionFactory;
import com.github.kislayverma.dredd.action.async.ActionQueue;
import com.github.kislayverma.dredd.action.async.AsyncExecutionRequest;
import com.github.kislayverma.dredd.domain.Action;
import com.github.kislayverma.dredd.domain.ActionType;
import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.domain.Event;
import com.github.kislayverma.dredd.domain.Executor;
import com.github.kislayverma.dredd.domain.exception.AsyncTaskSubmissionException;

/**
 * This is the default implementation of the executor class.
 * @author kislay.verma
 */
public class BaseExecutor implements Executor {
    private final ActionFactory actionFactory;
    private final ActionQueue asyncQueue;

    public BaseExecutor(ActionFactory actionFactory, ActionQueue asyncQueue) {
        this.actionFactory = actionFactory;
        this.asyncQueue = asyncQueue;
    }

    @Override
    public Object execute(Action A, Entity E, Event T) {
        if (ActionType.SYNC.name().equalsIgnoreCase(A.getType().name())) {
            return processSyncAction(A, E, T);
        } else if (ActionType.SYNC.name().equalsIgnoreCase(A.getType().name())) {
            try {
                return processAsyncAction(A, E, T);
            } catch (AsyncTaskSubmissionException ex) {
                throw new RuntimeException("Failed to submit action execution request", ex);
            }
        } else {
            throw new IllegalArgumentException("Unknow action type " + A.getType().name());
        }
    }

    protected Object processSyncAction(Action A, Entity E, Event T) {
        Action action = actionFactory.getAction(A.getActionCode());
        if (action == null) {
            throw new IllegalArgumentException("No action configured for code " + A.getActionCode());
        } else {
            return action.execute(E, T);
        }
    }

    // This method submits an asynchronous execution request to the configured action 
    // queue for later processing.
    protected Object processAsyncAction(Action A, Entity E, Event T) throws AsyncTaskSubmissionException {
        AsyncExecutionRequest request = new AsyncExecutionRequest(E, T, A.getActionCode());
        asyncQueue.submitTask(request);

        return null;
    }
}
