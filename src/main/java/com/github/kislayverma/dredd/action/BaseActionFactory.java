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

/**
 * This models an action to be invoked on determination of a valid state.
 * @author kislay.verma
 */
public class BaseActionFactory implements ActionFactory {
    private final ActionRegistry registry;
    private static BaseActionFactory INSTANCE;

    private BaseActionFactory(ActionRegistry registry) {
        this.registry = registry;
    }

    public static ActionFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BaseActionFactory(new DefaultActionRegistry());
        }

        return INSTANCE;
    }

    @Override
    public Action getAction(String actionType) throws IllegalArgumentException {
        return this.registry.getAction(actionType);
    }
}
