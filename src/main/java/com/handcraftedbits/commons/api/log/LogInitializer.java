/**
 * Copyright (C) 2020 HandcraftedBits
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.handcraftedbits.commons.api.log;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * {@code LogInitializer} is used to initialize HandcraftedBits logging by forcing
 * <a href="https://docs.oracle.com/javase/9/docs/api/java/util/logging/package-summary.html">java.util.logging</a>
 * calls to pass through the common <a href="http://www.slf4j.org/">slf4j</a> implementation.
 */

public final class LogInitializer {
     private LogInitializer () {
     }

     /**
      * Initializes HandcraftedBits logging.
      */

     public static void initialize () {
          SLF4JBridgeHandler.removeHandlersForRootLogger();

          SLF4JBridgeHandler.install();
     }
}
