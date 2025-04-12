/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.cdap.wrangler.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ByteSizeParser class is responsible for parsing byte size strings, such as "1KB", "10MB", etc., 
 * and converting them into their corresponding long representation in bytes.
 * <p>
 * It supports the following units: K (Kilobytes), M (Megabytes), G (Gigabytes), and T (Terabytes).
 * If no unit is provided, the number is assumed to be in bytes.
 */
public class ByteSizeParser {
  private static final Pattern PATTERN = Pattern.compile("(?i)(\\d+(?:\\.\\d+)?)([KMGT]?)B");

  /**
   * Parses a byte size string into its corresponding long value in bytes.
   * 
   * @param input the byte size string to parse, e.g., "1K", "10MB", "3.5GB".
   * @return the equivalent byte value as a long.
   * @throws IllegalArgumentException if the input string is not a valid byte size format.
   */
  public static long parse(String input) {
    Matcher matcher = PATTERN.matcher(input.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid byte size format: " + input);
    }

    double number = Double.parseDouble(matcher.group(1));
    String unit = matcher.group(2).toUpperCase();

    switch (unit) {
      case "":
        return (long) number;
      case "K":
        return (long) (number * 1024);
      case "M":
        return (long) (number * 1024 * 1024);
      case "G":
        return (long) (number * 1024 * 1024 * 1024);
      case "T":
        return (long) (number * 1024L * 1024 * 1024 * 1024);
      default:
        throw new IllegalArgumentException("Unknown unit: " + unit);
    }
  }
} 

