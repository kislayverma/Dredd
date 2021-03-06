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
package com.github.kislayverma.dredd.entity;

import com.github.kislayverma.dredd.domain.Entity;

/**
 * This interface defines an entity source which is expected to retrieve the actual entity given its id.
 * @author kislay.verma
 * @param <E> Type of the entity this class retrieves
 */
public interface EntityProvider<E extends Entity> {
    E fetchEntity(String entityId);
    Class<E> getEntityType();
}
