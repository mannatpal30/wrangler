/*
 * Copyright © 2024 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.cdap.wrangler.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ByteSizeParserTest {

  @Test
  public void testParseBytesWithoutUnit() {
    assertEquals(128L, ByteSizeParser.parse("128B"));
  }

  @Test
  public void testParseKilobytes() {
    assertEquals(10240L, ByteSizeParser.parse("10KB"));
  }

  @Test
  public void testParseMegabytes() {
    assertEquals(1572864L, ByteSizeParser.parse("1.5MB"));
  }

  @Test
  public void testParseGigabytes() {
    assertEquals(2147483648L, ByteSizeParser.parse("2GB"));
  }

  @Test
  public void testInvalidFormat() {
    assertThrows(IllegalArgumentException.class, () -> {
      ByteSizeParser.parse("invalid");
    });
  }
}
