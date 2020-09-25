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

import lombok.NonNull;

/**
 * {@code LogAction} is used to address a deficiency in slf4j's fluent API.  Namely, that MDC values can't be added in a
 * fluent manner, meaning {@code logger.isXXXEnabled()} checks still have to be performed to set and clean up MDC
 * values.  This class helps eliminate those conditional checks from end user code.
 */

public final class LogAction {
     private LogAction () {
     }

     /**
      * Runs an action if the log level is DEBUG or higher.
     
      * @param logger a {@link Logger} object containing the logger used to determine the log level.
      * @param action a {@link Runnable} object containing the action to run.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void atDebug (@NonNull final Logger logger, @NonNull final Runnable action) {
          if (logger.isDebugEnabled()) {
               action.run();
          }
     }

     /**
      * Runs an action if the log level is ERROR or higher.
     
      * @param logger a {@link Logger} object containing the logger used to determine the log level.
      * @param action a {@link Runnable} object containing the action to run.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void atError (@NonNull final Logger logger, @NonNull final Runnable action) {
          if (logger.isErrorEnabled()) {
               action.run();
          }
     }

     /**
      * Runs an action if the log level is INFO or higher.
     
      * @param logger a {@link Logger} object containing the logger used to determine the log level.
      * @param action a {@link Runnable} object containing the action to run.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void atInfo (@NonNull final Logger logger, @NonNull final Runnable action) {
          if (logger.isInfoEnabled()) {
               action.run();
          }
     }

     /**
      * Runs an action if the log level is TRACE or higher.
     
      * @param logger a {@link Logger} object containing the logger used to determine the log level.
      * @param action a {@link Runnable} object containing the action to run.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void atTrace (@NonNull final Logger logger, @NonNull final Runnable action) {
          if (logger.isTraceEnabled()) {
               action.run();
          }
     }

     /**
      * Runs an action if the log level is WARN or higher.
     
      * @param logger a {@link Logger} object containing the logger used to determine the log level.
      * @param action a {@link Runnable} object containing the action to run.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public static void atWarn (@NonNull final Logger logger, @NonNull final Runnable action) {
          if (logger.isWarnEnabled()) {
               action.run();
          }
     }
}
