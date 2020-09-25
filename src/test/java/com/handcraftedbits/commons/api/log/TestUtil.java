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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;

final class TestUtil {
     static final String FORMAT_JSON = "json";
     static final String FORMAT_TEXT = "text";

     private TestUtil () {
     }

     static void checkOutput (final String resourceName, final ByteArrayOutputStream output, final String format)
          throws Exception {
          final String expected;
          String result;

          try (InputStream input = TestUtil.class.getResourceAsStream("/testdata/" + resourceName)) {
               expected = IOUtils.toString(input, StandardCharsets.UTF_8);
          }

          result = output.toString(StandardCharsets.UTF_8);

          if (format.equals(TestUtil.FORMAT_JSON)) {
               result = result.replaceAll("\"@timestamp[^,]*,", "");
          }

          Assertions.assertEquals(expected, result);
     }

     static void resetLogConfiguration (final String level, final String format) throws JoranException {
          final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
          final ContextInitializer initializer = new ContextInitializer(context);

          System.setProperty("LOG_FORMAT", format);
          System.setProperty("LOG_LEVEL", level);

          context.reset();

          initializer.configureByResource(initializer.findURLOfDefaultConfigurationFile(true));
     }

     static void resetLogConfiguration (final Level level, final String format) throws JoranException {
          TestUtil.resetLogConfiguration(level.name(), format);
     }
}
