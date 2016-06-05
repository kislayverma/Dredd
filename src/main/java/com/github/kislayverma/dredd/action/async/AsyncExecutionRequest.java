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

import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.domain.Event;
import java.io.Serializable;

/**
 * This class models a request for asynchronous execution of an action with the given entity and event.
 * @author kislay.verma
 */
public class AsyncExecutionRequest implements Serializable{
    private final Entity entity;
    private final Event event;
    private final String actionType;

    public AsyncExecutionRequest(Entity entity, Event event, String actionType) {
        this.entity = entity;
        this.event = event;
        this.actionType = actionType;
    }

    public Entity getEntity() {
        return entity;
    }

    public Event getEvent() {
        return event;
    }

    public String getActionType() {
        return actionType;
    }
}
