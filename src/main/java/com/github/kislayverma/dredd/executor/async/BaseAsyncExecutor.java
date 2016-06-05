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
package com.github.kislayverma.dredd.executor.async;

import com.github.kislayverma.dredd.domain.Action;
import com.github.kislayverma.dredd.domain.Entity;
import com.github.kislayverma.dredd.action.BaseActionFactory;
import com.github.kislayverma.dredd.action.async.AsyncActionQueue;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the default implementation of executor for async actions. This reads data from the configured
 * AsycActionQueue and executes the tasks in it.
 * @author kislay.verma
 */
public class BaseAsyncExecutor {
    private final List<Thread> consumerThreadList;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAsyncExecutor.class);

    public BaseAsyncExecutor(AsyncActionQueue taskList) {
        this.consumerThreadList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Thread x = new Thread(new BaseAsyncExecutorWorker(
                taskList, BaseActionFactory.getInstance()), "Dredd-Async-Worker-" + i);
            x.start();
            consumerThreadList.add(x);
            LOGGER.info("Started thread " + x.getName());
        }
    }
}
