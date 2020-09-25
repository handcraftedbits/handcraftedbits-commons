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
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

@ResourceLock(value = Resources.SYSTEM_ERR, mode = ResourceAccessMode.READ_WRITE)
@ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
@ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
public final class LoggerTest {
     private ByteArrayOutputStream systemErr;
     private PrintStream systemErrBackup;
     private ByteArrayOutputStream systemOut;
     private PrintStream systemOutBackup;

     @BeforeAll
     public static void initializeLogging () throws Exception {
          LogInitializer.initialize();
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

     @ParameterizedTest
     @ValueSource(strings = { TestUtil.FORMAT_JSON, TestUtil.FORMAT_TEXT })
     public void testJCL (final String format) throws Exception {
          final Log logger;

          TestUtil.resetLogConfiguration(Level.TRACE, format);

          logger = LogFactory.getLog(getClass());

          logger.trace("trace from JCL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.trace("trace from JCL with MDC");
          MDC.clear();

          logger.debug("debug from JCL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.debug("debug from JCL with MDC");
          MDC.clear();

          logger.info("info from JCL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.info("info from JCL with MDC");
          MDC.clear();

          logger.warn("warn from JCL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.warn("warn from JCL with MDC");
          MDC.clear();

          logger.error("error from JCL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.error("error from JCL with MDC");
          MDC.clear();

          TestUtil.checkOutput("LoggerTest/" + format + "/testJCL.err", this.systemErr, format);
          TestUtil.checkOutput("LoggerTest/" + format + "/testJCL.out", this.systemOut, format);
     }

     @ParameterizedTest
     @ValueSource(strings = { TestUtil.FORMAT_JSON, TestUtil.FORMAT_TEXT })
     public void testJUL (final String format) throws Exception {
          final java.util.logging.Logger logger;

          TestUtil.resetLogConfiguration(Level.TRACE, format);

          logger = java.util.logging.Logger.getLogger(getClass().getName());

          logger.finest("finest from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.finest("finest from JUL with MDC");
          MDC.clear();

          logger.finer("finer from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.finer("finer from JUL with MDC");
          MDC.clear();

          logger.fine("fine from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.fine("fine from JUL with MDC");
          MDC.clear();

          logger.info("info from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.info("info from JUL with MDC");
          MDC.clear();

          logger.warning("warning from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.warning("warning from JUL with MDC");
          MDC.clear();

          logger.severe("severe from JUL");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.severe("severe from JUL with MDC");
          MDC.clear();

          TestUtil.checkOutput("LoggerTest/" + format + "/testJUL.err", this.systemErr, format);
          TestUtil.checkOutput("LoggerTest/" + format + "/testJUL.out", this.systemOut, format);
     }

     @ParameterizedTest
     @ValueSource(strings = { TestUtil.FORMAT_JSON, TestUtil.FORMAT_TEXT })
     public void testLog4j (final String format) throws Exception {
          final Logger logger;

          TestUtil.resetLogConfiguration(Level.TRACE, format);

          logger = LogManager.getLogger(getClass());

          logger.trace("trace from Log4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.trace("trace from Log4j with MDC");
          MDC.clear();

          logger.debug("debug from Log4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.debug("debug from Log4j with MDC");
          MDC.clear();

          logger.info("info from Log4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.info("info from Log4j with MDC");
          MDC.clear();

          logger.warn("warn from Log4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.warn("warn from Log4j with MDC");
          MDC.clear();

          logger.error("error from Log4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.error("error from Log4j with MDC");
          MDC.clear();

          TestUtil.checkOutput("LoggerTest/" + format + "/testLog4j.err", this.systemErr, format);
          TestUtil.checkOutput("LoggerTest/" + format + "/testLog4j.out", this.systemOut, format);
     }

     @ParameterizedTest
     @ValueSource(strings = { TestUtil.FORMAT_JSON, TestUtil.FORMAT_TEXT })
     public void testSlf4j (final String format) throws Exception {
          final org.slf4j.Logger logger;

          TestUtil.resetLogConfiguration(Level.TRACE, format);

          logger = LoggerFactory.getLogger(getClass());

          logger.trace("trace from Slf4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.trace("trace from Slf4j with MDC");
          MDC.clear();

          logger.debug("debug from Slf4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.debug("debug from Slf4j with MDC");
          MDC.clear();

          logger.info("info from Slf4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.info("info from Slf4j with MDC");
          MDC.clear();

          logger.warn("warn from Slf4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.warn("warn from Slf4j with MDC");
          MDC.clear();

          logger.error("error from Slf4j");
          MDC.put("key1", "value1");
          MDC.put("key2", "value2");
          logger.error("error from Slf4j with MDC");
          MDC.clear();

          TestUtil.checkOutput("LoggerTest/" + format + "/testSlf4j.err", this.systemErr, format);
          TestUtil.checkOutput("LoggerTest/" + format + "/testSlf4j.out", this.systemOut, format);
     }
}
