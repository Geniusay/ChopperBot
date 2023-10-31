package org.example.util;

/**
 * @author Genius
 * @date 2023/10/25 21:31
 **/
public class StringUtil {
    public static String lowerCaseFirstLetter(String input){
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Character.toLowerCase(input.charAt(0)) + input.substring(1);
    }
}
