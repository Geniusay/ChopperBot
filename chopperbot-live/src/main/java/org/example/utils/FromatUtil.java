package org.example.utils;

import java.time.Duration;
import java.util.regex.Pattern;

/**
 * 格式工具类
 * @author 燧枫
 * @date 2023/8/3 12:51
 */
public class FromatUtil {

    private static final Pattern TIME_PATTERN = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");

    // 00:25:44->1544，01:45:24->6324
    public static long convertToSeconds(String timeString) {
        if (TIME_PATTERN.matcher(timeString).matches()) {
            String[] parts = timeString.split(":");
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[1]);
            long seconds = Long.parseLong(parts[2]);
            return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds).getSeconds();
        } else {
            try {
                return Long.parseLong(timeString);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid time format: " + timeString);
            }
        }
    }
}
