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
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a factory class to register all the entity retrievers and to get entities 
 * without worrying about the inner details.
 * @author kislay.verma
 */
public class EntityProviderFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityProviderFactory.class);
    private Map<String, EntityProvider> retrieverMap;

    /**
     * Registers an {@link EntityProvider} instance in the factory.
     * @param retriever The EntityProvider to register
     */
    void registerEntityRetriever(EntityProvider retriever) {
        if (retriever != null) {
            this.retrieverMap.put(retriever.getEntityType().getName(), retriever);
        }
    }

    Entity getEntity(String entityId, Class entityType) {
        EntityProvider retriever = this.retrieverMap.get(entityType.getName());
        if (retriever == null) {
            LOGGER.error("No defined way to retrive entities of type " + entityType.getName());
            throw new RuntimeException("No defined way to retrive entities of type " + entityType.getName());
        } else {
            return retriever.fetchEntity(entityId);
        }
    }
}
