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
package com.handcraftedbits.commons.api.exception;

import java.util.Locale;

import com.handcraftedbits.commons.api.resource.ResourceBundleHelper;

import lombok.NonNull;

/**
 * {@code LocalizableRuntimeException} is used to simplify exception handling by allowing {@link RuntimeException}
 * messages to be translated at the point at which they are caught.
 */

public class LocalizableRuntimeException extends RuntimeException {
     private static final long serialVersionUID = -304442281204922645L;

     private final Object[] args;
     private final ResourceBundleHelper bundleHelper;
     private final String key;

     /**
      * Creates a {@code LocalizableRuntimeException}.
     
      * @param bundleHelper a {@link ResourceBundleHelper} object containing the resource bundle helper used to look up
      *                     the message.
      * @param key a {@link String} object containing the message key to look up.
      * @param args an array of {@link Object}s containing any values to substitute in the message.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public LocalizableRuntimeException (@NonNull final ResourceBundleHelper bundleHelper, @NonNull final String key,
          @NonNull final Object... args) {
          this.args = args;
          this.bundleHelper = bundleHelper;
          this.key = key;
     }

     @Override
     public String getLocalizedMessage () {
          return getLocalizedMessage(Locale.getDefault());
     }

     /**
      * Retrieves an interpolated message using a custom locale.
      * 
      * @param locale a {@link Locale} object containing the locale to use.
      * @return a {@link String} object containing the desired message or {@code"!" + key + "!"} if the message could
                not be found or loaded.
      * @throws NullPointerException if any parameter is {@code null}.
      */

     public String getLocalizedMessage (@NonNull final Locale locale) {
          return this.bundleHelper.getMessage(locale, this.key, this.args);
     }

     @Override
     public String getMessage () {
          return getLocalizedMessage();
     }
}
