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
 * @param <S> Evaluated state object
 * @param <E> Entity on which event has occurred
 * @param <T> The event which has occurred
 */
public interface Executor<S, E, T> {
    /**
     * This method invokes the appropriate action based on the given inputs.
     * @param S
     * @param E
     * @param T
     * @return 
     */
    Object execute(State S, Entity E, Event T);
}
