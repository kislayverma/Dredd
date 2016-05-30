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
package com.github.kislayverma.dredd.domain.evaluator;

import com.github.kislayverma.dredd.domain.Action;
import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.domain.Evaluator;
import com.github.kislayverma.dredd.domain.Event;

/**
 * This is the default implementation of the state evaluator.
 * @author kislay.verma
 * @param <E> Entity on which event has occurred
 * @param <T> The event which has occurred
 */
public class BaseEvaluator<E extends Entity, T extends Event> implements Evaluator<E, T> {

    private RuleEngine engine;

    @Override
    public Action evaluate(Entity E, Event T) {
        engine.getRule(E, T);
        return null;
    }
}
