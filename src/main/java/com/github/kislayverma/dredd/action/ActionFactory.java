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
 * This class is a factory interface for obtaining the needed action instances without 
 * exposing the details of their construction to the clients. 
 * @author kislay.verma
 */
public interface ActionFactory {
    /**
     * This method returns the action class given its unique identifier.
     * 
     * @param actionCode Unique identifier for an action
     * @return The action class corresponding to the given type
     * @throws IllegalArgumentException if no action is configured for the given type
     */
    Action getAction(String actionCode) throws IllegalArgumentException;
}
