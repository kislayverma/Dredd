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
package com.github.kislayverma.dredd.action.provider;

import com.github.kislayverma.dredd.domain.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRegisterableActionProvider implements RegisterableActionProvider {
    private final Map<String, Action> actionRegister;
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRegisterableActionProvider.class);

    public DefaultRegisterableActionProvider() {
        LOGGER.info("Initializing default registerable action provider...");
        this.actionRegister = new ConcurrentHashMap<>();
    }

    @Override
    public void registerAction(Action action) {
        this.actionRegister.put(action.getActionCode(), action);
        LOGGER.info("Registered action type " + action.getActionCode());
    }

    @Override
    public List<Action> getActions() {
        return new ArrayList<>(this.actionRegister.values());
    }
}
