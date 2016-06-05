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
package com.github.kislayverma.dredd.action;

import com.github.kislayverma.dredd.domain.Action;
import com.github.kislayverma.dredd.domain.ActionType;
import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.domain.Event;

/**
 * This is the default implementation of an asynchronous action which return to the
 * executor immediately and processes its job in the background on a separate thread.
 * @author kislay.verma
 * @param <E> Entity on which event occurred
 * @param <T> Event which occurred
 */
public abstract class BaseAsyncAction<E extends Entity, T extends Event> implements Action<E, T> {
    @Override
    public ActionType getType() {
        return ActionType.ASYNC;
    }
}
