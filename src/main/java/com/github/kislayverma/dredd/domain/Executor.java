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
package com.github.kislayverma.dredd.domain;

/**
 * This class defines an action executor
 * @author kislay.verma
 */
public interface Executor {
    /**
     * This method invokes the appropriate action based on the given inputs.
     * @param A Evaluated action object
     * @param E Entity on which event has occurred
     * @param T The event which has occurred
     * @return The result of the executed action
     */
    Object execute(Action A, Entity E, Event T);
}
