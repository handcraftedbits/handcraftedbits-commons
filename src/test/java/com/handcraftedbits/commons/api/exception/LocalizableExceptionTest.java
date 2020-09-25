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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.commons.api.resource.ResourceBundleHelper;

public final class LocalizableExceptionTest {
     private final ResourceBundleHelper bundleHelper = new ResourceBundleHelper("messages.test");

     @Nested
     @DisplayName("Constructor tests")
     public final class Constructor {
          @Nested
          @DisplayName("Given a missing value for the parameter 'args'")
          public final class GivenParamArgsMissing {
               @Test
               @DisplayName("no exception should be thrown")
               public void shouldNotThrowNullPointerException () {
                    new LocalizableException(bundleHelper, "key");
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'args'")
          public final class GivenParamArgsNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> {
                         new LocalizableException(bundleHelper, "key", (Object[]) null);
                    });
               }
          }

          @Nested
          @DisplayName("Given a single null value for the parameter 'args'")
          public final class GivenParamArgsSingleNullValue {
               @Test
               @DisplayName("no exception should be thrown")
               public void shouldNotThrowNullPointerException () {
                    new LocalizableException(bundleHelper, "key", new Object[] { null });
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'bundleHelper'")
          public final class GivenParamBundleHelperNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> {
                         new LocalizableException(null, "key");
                    });
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'key'")
          public final class GivenParamKeyNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> {
                         new LocalizableException(bundleHelper, null);
                    });
               }
          }
     }

     @Nested
     @DisplayName("getLocalizedMessage() tests")
     public final class GetLocalizedMessage {
          private final LocalizableException exception = new LocalizableException(bundleHelper, "test.message", "abc");

          @Test
          @DisplayName("calling getLocalizedMessage() should return the correct message when a locale is given")
          public void shouldReturnCorrectMessageForLocale () {
               Assertions.assertEquals("German test message, value=abc", exception.getLocalizedMessage(Locale.GERMANY));
          }

          @Test
          @DisplayName("calling getLocalizedMessage() should return the correct message when no locale is given")
          public void shouldReturnCorrectMessageForNoLocale () {
               Assertions.assertEquals("Default test message, value=abc", exception.getLocalizedMessage());
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'locale'")
          public final class GivenParamKeyLocaleNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> {
                         exception.getLocalizedMessage(null);
                    });
               }
          }
     }

     @Nested
     @DisplayName("getMessage() tests")
     public final class GetMessage {
          private final LocalizableException exception = new LocalizableException(bundleHelper, "test.message", "abc");

          @Test
          @DisplayName("calling getMessage() should return the correct message")
          public void shouldReturnCorrectMessageForLocale () {
               Assertions.assertEquals("Default test message, value=abc", exception.getMessage());
          }
     }
}
