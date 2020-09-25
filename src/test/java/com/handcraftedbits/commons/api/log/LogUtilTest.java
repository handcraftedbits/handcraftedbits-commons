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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class LogUtilTest {
     @Nested
     @DisplayName("setLevel() tests")
     public final class SetLevel {
          @Nested
          @DisplayName("Given a non-Logback Logger object")
          public final class GivenInvalidLoggerType {
               private final Logger log = Mockito.mock(Logger.class);

               @Test
               @DisplayName("nothing should happen")
               public void shouldDoNothing () {
                    // Not a great testcase since we can only verify this through code coverage.

                    LogUtil.setLevel(this.log, Level.TRACE);
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'level'")
          public final class GivenParamLevelNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamLevelNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogUtil.setLevel(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogUtil.setLevel(null, Level.DEBUG));
               }
          }

          @Nested
          @DisplayName("Given valid parameters")
          @ResourceLock(value = Resources.SYSTEM_ERR, mode = ResourceAccessMode.READ_WRITE)
          @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
          public final class GivenValidParameters {
               private final Logger log = LoggerFactory.getLogger(GivenValidParameters.class);
               private ByteArrayOutputStream systemErr;
               private PrintStream systemErrBackup;
               private ByteArrayOutputStream systemOut;
               private PrintStream systemOutBackup;

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

               @Test
               @DisplayName("changing the logger level should succeed")
               public void shouldChangeLevelSuccessfully () throws Exception {
                    LogUtil.setLevel(this.log, Level.TRACE);

                    this.log.trace("trace");
                    this.log.debug("debug");
                    this.log.error("error");
                    this.log.info("info");
                    this.log.warn("warn");

                    LogUtil.setLevel(this.log, Level.WARN);

                    this.log.trace("trace");
                    this.log.debug("debug");
                    this.log.error("error");
                    this.log.info("info");
                    this.log.warn("warn");

                    TestUtil.checkOutput("LogUtilTest/SetLevel.GivenValidParameters.shouldChangeLevelSuccessfully.err",
                         this.systemErr, TestUtil.FORMAT_TEXT);
                    TestUtil.checkOutput("LogUtilTest/SetLevel.GivenValidParameters.shouldChangeLevelSuccessfully.out",
                         this.systemOut, TestUtil.FORMAT_TEXT);
               }
          }
     }

     @Nested
     @DisplayName("setRootLevel() tests")
     public final class SetRootLevel {
          @Nested
          @DisplayName("Given a null value for the parameter 'level'")
          public final class GivenParamLevelNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogUtil.setRootLevel(null));
               }
          }

          @Nested
          @DisplayName("Given valid parameters")
          @ResourceLock(value = Resources.SYSTEM_ERR, mode = ResourceAccessMode.READ_WRITE)
          @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
          public final class GivenValidParameters {
               private final Logger log = LoggerFactory.getLogger(GivenValidParameters.class);
               private ByteArrayOutputStream systemErr;
               private PrintStream systemErrBackup;
               private ByteArrayOutputStream systemOut;
               private PrintStream systemOutBackup;

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

               @Test
               @DisplayName("changing the logger level should succeed")
               public void shouldChangeLevelSuccessfully () throws Exception {
                    LogUtil.setRootLevel(Level.TRACE);

                    this.log.trace("trace");
                    this.log.debug("debug");
                    this.log.error("error");
                    this.log.info("info");
                    this.log.warn("warn");

                    LogUtil.setRootLevel(Level.WARN);

                    this.log.trace("trace");
                    this.log.debug("debug");
                    this.log.error("error");
                    this.log.info("info");
                    this.log.warn("warn");

                    TestUtil.checkOutput("LogUtilTest/SetRootLevel.GivenValidParameters.shouldChangeLevelSuccessfully" +
                         ".err", this.systemErr, TestUtil.FORMAT_TEXT);
                    TestUtil.checkOutput("LogUtilTest/SetRootLevel.GivenValidParameters.shouldChangeLevelSuccessfully" +
                         ".out", this.systemOut, TestUtil.FORMAT_TEXT);
               }
          }
     }
}
