package org.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dhx
 * @date 2023/8/4 19:36
 */
public class RegexUtil {
    public static String[] match(String input, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(input);

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches.toArray(new String[0]);
    }
}
