package com.calcul.diabetif.commun.util;

import java.util.ArrayList;
import java.util.List;

/**
 * String Utilities
 * 
 * @author saravuth
 * 
 */
public class StringUtils {

    public static final String EMPTY = "";

    /**
     * Find first not null value from input parameters
     */
    public static String coalesce(String... args) {
        for (String string : args) {
            if (isNotEmpty(string)) {
                return string;
            }
        }
        return EMPTY;
    }

    /**
     * Return true if input string is not null value and not empty string
     */
    public static boolean isNotEmpty(String arg) {
        return arg != null && arg.length() > 0;
    }

    /**
     * Convert an array of strings to one string.
     */
    public static String join(String[] s, String separator) {
        StringBuffer result = new StringBuffer();
        if (s.length > 0) {
            result.append(s[0]);
            for (int i = 1; i < s.length; i++) {
                result.append(separator);
                result.append(s[i]);
            }
        }
        return result.toString();
    }

    /**
     * Convert an list of strings to one string.
     */
    public static String join(List<String> list, String separator) {
        StringBuilder b = new StringBuilder();
        for (String item : list) {
            b.append(separator).append(item);
        }
        return b.toString().substring(separator.length());
    }

    public static String[] generateListOfNumber(int max) {
        List<String> lists = new ArrayList<String>();
        for (int i = 0; i <= max; i++) {
            lists.add(String.valueOf(i));
        }
        return lists.toArray(new String[lists.size()]);
    }
    
    public static String capitalize(String word) {
        if (!StringUtils.isNotEmpty(word)) {
            return word;
        }
        final StringBuilder result = new StringBuilder(word.length());
        result.append(Character.toUpperCase(word.charAt(0)));
        result.append(word.substring(1).toLowerCase());
        return result.toString();
    }

}
