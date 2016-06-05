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
import java.util.List;

/**
 * This interface models registry which return objects of all valid action classes of a system.
 * The mechanics of how these instances are obtained to each specific implementation.
 * e.g. Applications using Spring may load all beans of a certain super type from the context.
 */
public interface ActionProvider {
    /**
     * This method returns a list of objects of all available actions.
     * @return list of objects of all available action classes
     */
    List<Action> getActions();
}
