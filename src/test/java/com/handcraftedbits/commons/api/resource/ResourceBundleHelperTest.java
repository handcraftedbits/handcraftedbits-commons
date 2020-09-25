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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

public class ResourceBundleHelperTest {
     // Tests specific to the ResourceBundleHelper constructor.

     @Nested
     @DisplayName("Constructor tests")
     public final class Constructor {
          @Nested
          @DisplayName("Given a null value for the parameter 'bundleName'")
          public final class GivenParamBundleNameNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> new ResourceBundleHelper(null));
               }
          }
     }

     @Nested
     @DisplayName("getMessage() tests")
     public final class GetMessage {
          private final ResourceBundleHelper helper = new ResourceBundleHelper("test");

          @Nested
          @DisplayName("Given a null value for the parameter 'key'")
          public final class GivenParamKeyNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class,
                         () -> helper.getMessage(null, new Object[] {}));
                    Assertions.assertThrows(NullPointerException.class,
                         () -> helper.getMessage(Locale.getDefault(), null, new Object[] {}));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'locale'")
          public final class GivenParamLocaleNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class,
                         () -> helper.getMessage(null, "key", new Object[] {}));
               }
          }
     }

     // Tests for conditions that occur when a resource bundle is missing.

     @Nested
     @DisplayName("Given a missing bundle")
     @ResourceLock(value = Resources.SYSTEM_ERR, mode = ResourceAccessMode.READ_WRITE)
     @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenMissingBundle {
          private final ResourceBundleHelper helper = new ResourceBundleHelper("missing");
          private ByteArrayOutputStream systemErr;
          private PrintStream systemErrBackup;
          private ByteArrayOutputStream systemOut;
          private PrintStream systemOutBackup;

          private void checkOutput (final String resourceName, final ByteArrayOutputStream output) throws Exception {
               final String expected;

               try (InputStream input = getClass().getResourceAsStream("/testdata/" + resourceName)) {
                    expected = IOUtils.toString(input, StandardCharsets.UTF_8);
               }

               Assertions.assertTrue(output.toString(StandardCharsets.UTF_8).startsWith(expected));
          }

          @Test
          @DisplayName("calling getMessage() should fail")
          public void getMessageShouldFail () throws Exception {
               Assertions.assertEquals("!message!", this.helper.getMessage("message"));

               checkOutput("ResourceBundleHelperTest/GivenMissingBundle.getMessageShouldFail.err", this.systemErr);
          }

          @AfterEach
          public void restoreSystemErrSystemOut () throws Exception {
               System.setErr(this.systemErrBackup);
               System.setOut(this.systemOutBackup);
          }

          @BeforeEach
          public void setSystemErrSystemOut () {
               this.systemErr = new ByteArrayOutputStream();
               this.systemErrBackup = System.err;
               this.systemOut = new ByteArrayOutputStream();
               this.systemOutBackup = System.out;

               System.setErr(new PrintStream(this.systemErr));
               System.setOut(new PrintStream(this.systemOut));
          }
     }

     // Tests for conditions that occur when a resource bundle is available.

     @Nested
     @DisplayName("Given a valid bundle")
     public final class GivenValidBundle {
          private final ResourceBundleHelper helper = new ResourceBundleHelper("messages.test");

          @Test
          @DisplayName("calling getMessage() should succeed for the default locale")
          public void getMessageShouldSucceedForDefaultLocale () {
               Assertions.assertEquals("Default test message, value=abc",
                    this.helper.getMessage("test.message", "abc"));
          }

          @Test
          @DisplayName("calling getMessage() should succeed for a custom locale")
          public void getMessageShouldSucceedForCustomLocale () {
               Assertions.assertEquals("German test message, value=abc",
                    this.helper.getMessage(Locale.GERMANY, "test.message", "abc"));
          }
     }
}
