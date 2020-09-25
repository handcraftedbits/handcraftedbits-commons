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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import lombok.NonNull;

/**
 * {@code LogUtil} is used to perform various actions on slf4j {@link Logger} objects.
 */

public final class LogUtil {
     private LogUtil () {
     }

     /** 
      * Sets the level for a logger.
     
      * @param logger a {@link Logger} object containing the logger to use.
      * @param level a {@link Level} object containing the level to set the logger level to.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void setLevel (@NonNull final Logger logger, @NonNull final Level level) {
          if (logger instanceof ch.qos.logback.classic.Logger) {
               final ch.qos.logback.classic.Level logbackLevel = ch.qos.logback.classic.Level.toLevel(level.toString());

               ((ch.qos.logback.classic.Logger) logger).setLevel(logbackLevel);
          }
     }

     /** 
      * Sets the level for the root logger.
      *
      * @param level a {@link Level} object containing the level to set the root logger level to.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void setRootLevel (@NonNull final Level level) {
          LogUtil.setLevel(LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME), level);
     }
}
