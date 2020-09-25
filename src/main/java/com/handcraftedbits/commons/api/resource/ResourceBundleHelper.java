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
package com.handcraftedbits.commons.api.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.MDC;

import com.handcraftedbits.commons.api.log.LogAction;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code ResourceBundleHelper} is used to simplify working with {@link ResourceBundle}s when multiple locales and
 * string interpolation are involved.
 */

@Slf4j
public final class ResourceBundleHelper {
     private final String bundleName;

     /**
      * Creates a {@ResourceBundleHelper}.
     
      * @param bundleName a {@link String} object containing the name of the bundle to use.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public ResourceBundleHelper (@NonNull final String bundleName) {
          this.bundleName = bundleName;
     }

     /**
      * Retrieves an interpolated message from a resource bundle using the default locale.
     
      * @param key a {@link String} object containing the message key to look up.
      * @param args an array of {@link Object}s containing any values to substitute in the message.
      * @return a {@link String} object containing the desired message or {@code"!" + key + "!"} if the message could
                not be found or loaded.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public String getMessage (@NonNull final String key, @NonNull final Object... args) {
          return getMessage(Locale.getDefault(), key, args);
     }

     /**
      * Retrieves an interpolated message from a resource bundle using a specific locale.
     
      * @param locale a {@link Locale} object containing the locale to use.
      * @param key a {@link String} object containing the message key to look up.
      * @param args an array of {@link Object}s containing any values to substitute in the message.
      * @return a {@link String} object containing the desired message or {@code"!" + key + "!"} if the message could
                not be found or loaded.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public String getMessage (@NonNull final Locale locale, @NonNull final String key, @NonNull final Object... args) {
          try {
               final String message = ResourceBundle.getBundle(this.bundleName, locale).getString(key);

               return MessageFormat.format(message, args);
          }

          catch (final Throwable e) {
               LogAction.atWarn(log, () -> {
                    MDC.put("bundleName", this.bundleName);
                    MDC.put("key", key);
                    MDC.put("locale", locale.toString());

                    log.warn("Unable to find message", e);

                    MDC.remove("bundleName");
                    MDC.remove("key");
                    MDC.remove("locale");
               });

               // Can't throw an exception, so return a String that indicates failure.

               return "!" + key + "!";
          }
     }
}
