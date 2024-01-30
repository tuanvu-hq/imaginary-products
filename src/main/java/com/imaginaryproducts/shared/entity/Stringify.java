package com.imaginaryproducts.shared.entity;

import java.util.LinkedHashMap;

public class Stringify {
    /**
     * Returns a formatted object like JSON.
     *
     * @param className Name of the class.
     * @param values A map of displayed properties.
     * @return Returns a formatted object.
     * */
    public static String build(String className, LinkedHashMap<String, Object> values) {
        StringBuilder s = new StringBuilder()
                .append(String.format("\n%s { \n", className));

        values.forEach((k,v) -> {
            s.append(setProperty(k, v));
        });

        s.append("}");

        return s.toString();
    }

    /**
     * Formats the key/value pair like in JSON.
     *
     * @param key Displays the key.
     * @param value Displays the value.
     * @return Returns a formatted property display.
     * */
    private static String setProperty(String key, Object value) {
        return String.format("    %s: %s \n", key, value);
    }
}
