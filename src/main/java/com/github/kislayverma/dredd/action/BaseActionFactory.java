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

import com.github.kislayverma.dredd.action.provider.DefaultRegisterableActionProvider;
import com.github.kislayverma.dredd.action.provider.ActionProvider;
import com.github.kislayverma.dredd.domain.Action;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is the default implementation of the {@link ActionFactory} interfaces. It uses 
 * a given implementation of {@link ActionProvider} to provide clients the action instances they need.
 * 
 * @author kislay.verma
 */
public class BaseActionFactory implements ActionFactory {
    private final ActionProvider provider;
    private final Map<String, Action> actionMap;
    private static BaseActionFactory INSTANCE;

    private BaseActionFactory(ActionProvider provider) {
        this.provider = provider;
        actionMap = new ConcurrentHashMap<>();

        for (Action action : provider.getActions()) {
            actionMap.put(action.getActionCode(), action);
        }
    }

    public static ActionFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BaseActionFactory(new DefaultRegisterableActionProvider());
        }

        return INSTANCE;
    }

    @Override
    public Action getAction(String actionCode) throws IllegalArgumentException {
        return this.actionMap.get(actionCode);
    }
}
