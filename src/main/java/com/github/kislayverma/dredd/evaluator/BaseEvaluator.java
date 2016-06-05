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
package com.github.kislayverma.dredd.evaluator;

import com.github.kislayverma.dredd.action.ActionFactory;
import com.github.kislayverma.dredd.domain.Action;
import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.domain.Evaluator;
import com.github.kislayverma.dredd.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the default implementation of the state evaluator.
 * @author kislay.verma
 * @param <E> Entity on which event has occurred
 * @param <T> The event which has occurred
 */
public class BaseEvaluator<E extends Entity, T extends Event> implements Evaluator<E, T> {

    private final RuleEngine engine;
    private final ActionFactory actionfactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEvaluator.class);

    public BaseEvaluator(RuleEngine engine, ActionFactory actionfactory) {
        this.engine = engine;
        this.actionfactory = actionfactory;
    }

    @Override
    public Action evaluate(Entity E, Event T) {
        String ruleOutput = engine.getRule(E, T);
        if (ruleOutput == null) {
            LOGGER.error("No action defined for combination of entity " + E + " and event " + T);
            return null;
        }

        return actionfactory.getAction(ruleOutput);
    }
}
