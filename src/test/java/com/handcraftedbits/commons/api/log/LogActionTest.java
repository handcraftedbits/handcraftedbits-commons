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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import lombok.AllArgsConstructor;
import lombok.Data;

public final class LogActionTest {
     // Tests specific to atDebug().

     @Nested
     @DisplayName("atDebug() tests")
     public final class AtDebug {
          @Nested
          @DisplayName("Given a null value for the parameter 'action'")
          public final class GivenParamActionNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamActionNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atDebug(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atDebug(null, () -> {
                    }));
               }
          }
     }

     // Tests specific to atError().

     @Nested
     @DisplayName("atError() tests")
     public final class AtError {
          @Nested
          @DisplayName("Given a null value for the parameter 'action'")
          public final class GivenParamActionNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamActionNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atError(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atError(null, () -> {
                    }));
               }
          }
     }

     // Tests specific to atInfo().

     @Nested
     @DisplayName("atInfo() tests")
     public final class AtInfo {
          @Nested
          @DisplayName("Given a null value for the parameter 'action'")
          public final class GivenParamActionNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamActionNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atInfo(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atInfo(null, () -> {
                    }));
               }
          }
     }

     // Tests specific to atTrace().

     @Nested
     @DisplayName("atTrace() tests")
     public final class AtTrace {
          @Nested
          @DisplayName("Given a null value for the parameter 'action'")
          public final class GivenParamActionNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamActionNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atTrace(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atTrace(null, () -> {
                    }));
               }
          }
     }

     // Tests specific to atWarn().

     @Nested
     @DisplayName("atWarn() tests")
     public final class AtWarn {
          @Nested
          @DisplayName("Given a null value for the parameter 'action'")
          public final class GivenParamActionNull {
               private final Logger log = LoggerFactory.getLogger(GivenParamActionNull.class);

               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atWarn(this.log, null));
               }
          }

          @Nested
          @DisplayName("Given a null value for the parameter 'logger'")
          public final class GivenParamLoggerNull {
               @Test
               @DisplayName("a NullPointerException should be thrown")
               public void shouldThrowNullPointerException () {
                    Assertions.assertThrows(NullPointerException.class, () -> LogAction.atWarn(null, () -> {
                    }));
               }
          }
     }

     // Tests for conditions that occur when log level is DEBUG.

     @Nested
     @DisplayName("Given the log level is set to DEBUG")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelDebug {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelDebug () throws Exception {
               TestUtil.resetLogConfiguration(Level.DEBUG, TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should result in an action being performed")
          public void atDebugShouldBeChanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should result in an action being performed")
          public void atErrorShouldBeChanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should result in an action being performed")
          public void atInfoShouldBeChanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should result in an action being performed")
          public void atTraceShouldBeUnchanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should result in an action being performed")
          public void atWarnShouldBeChanged () {
               reset();
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     // Tests for conditions that occur when log level is ERROR.

     @Nested
     @DisplayName("Given the log level is set to ERROR")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelError {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelError () throws Exception {
               TestUtil.resetLogConfiguration(Level.ERROR, TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should result not in an action being performed")
          public void atDebugShouldBeUnchanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should not result in an action being performed")
          public void atErrorShouldBeChanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should not result in an action being performed")
          public void atInfoShouldBeUnchanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should not result in an action being performed")
          public void atTraceShouldBeUnchanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should not result in an action being performed")
          public void atWarnShouldBeUnchanged () {
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     // Tests for conditions that occur when log level is INFO.

     @Nested
     @DisplayName("Given the log level is set to INFO")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelInfo {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelInfo () throws Exception {
               TestUtil.resetLogConfiguration(Level.INFO, TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should not result in an action being performed")
          public void atDebugShouldBeUnchanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should result in an action being performed")
          public void atErrorShouldBeChanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should result in an action being performed")
          public void atInfoShouldBeChanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should not result in an action being performed")
          public void atTraceShouldBeUnchanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should result in an action being performed")
          public void atWarnShouldBeChanged () {
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     // Tests for conditions that occur when log level is OFF.

     @Nested
     @DisplayName("Given the log level is set to OFF")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelOff {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelOff () throws Exception {
               TestUtil.resetLogConfiguration("OFF", TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should not result in an action being performed")
          public void atDebugShouldBeUnchanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should not result in an action being performed")
          public void atErrorShouldBeUnchanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should not result in an action being performed")
          public void atInfoShouldBeUnchanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should not result in an action being performed")
          public void atTraceShouldBeUnchanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should not result in an action being performed")
          public void atWarnShouldBeUnchanged () {
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     // Tests for conditions that occur when log level is TRACE.

     @Nested
     @DisplayName("Given the log level is set to TRACE")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelTrace {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelTrace () throws Exception {
               TestUtil.resetLogConfiguration(Level.TRACE, TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should result in an action being performed")
          public void atDebugShouldBeChanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should result in an action being performed")
          public void atErrorShouldBeChanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should result in an action being performed")
          public void atInfoShouldBeChanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should result in an action being performed")
          public void atTraceShouldBeChanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should result in an action being performed")
          public void atWarnShouldBeChanged () {
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     // Tests for conditions that occur when log level is WARN.

     @Nested
     @DisplayName("Given the log level is set to WARN")
     @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
     public final class GivenLogLevelWarn {
          private ValueHolder holder;
          private Logger log;

          public GivenLogLevelWarn () throws Exception {
               TestUtil.resetLogConfiguration(Level.WARN, TestUtil.FORMAT_TEXT);

               this.log = LoggerFactory.getLogger(getClass());
          }

          @Test
          @DisplayName("calling atDebug() should not result in an action being performed")
          public void atDebugShouldBeUnchanged () {
               LogAction.atDebug(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atError() should result in an action being performed")
          public void atErrorShouldBeChanged () {
               LogAction.atError(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atInfo() should not result in an action being performed")
          public void atInfoShouldBeUnchanged () {
               LogAction.atInfo(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atTrace() should not result in an action being performed")
          public void atTraceShouldBeUnchanged () {
               LogAction.atTrace(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(1, this.holder.getValue());
          }

          @Test
          @DisplayName("calling atWarn() should not result in an action being performed")
          public void atWarnShouldBeChanged () {
               LogAction.atWarn(this.log, () -> this.holder.setValue(2));

               Assertions.assertEquals(2, this.holder.getValue());
          }

          @BeforeEach
          public void reset () {
               this.holder = new ValueHolder(1);
          }
     }

     @AllArgsConstructor
     @Data
     private static final class ValueHolder {
          private int value;
     }
}
