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
 * This defines a state evaluator, which returns a state based on the input entity and event.
 * @author kislay.verma
 * @param <E> Entity on which event has occurred
 * @param <T> The event which has occurred
 */
public interface StateEvaluator<E, T> {
    /**
     * This method returns an object representing the entity's state based on the input entity and event.
     * @param E Entity on which event has occurred
     * @param T The event which has occurred
     * @return Object representing state if it could be evaluated, null if no valid state was found for inputs
     */
    State evaluate(Entity E, Event T);
}
