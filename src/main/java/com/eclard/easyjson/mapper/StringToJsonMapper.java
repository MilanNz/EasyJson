package com.eclard.easyjson.mapper;

import android.support.annotation.NonNull;

import com.eclard.easyjson.JsonArray;
import com.eclard.easyjson.JsonConstants;
import com.eclard.easyjson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Milan on 9/22/18.
 */
public class StringToJsonMapper {

    private StringToJsonMapper() {}

    public static Map<String, Object> stringToJsonObject(@NonNull String value) {
        Map<String, Object> mapperMap = new HashMap<>();

        if (value.charAt(0) != '{') {
            throw new IllegalArgumentException("String value is not json object!");
        }
        // Remove object brackets.
        value = value.substring(1, value.length() - 1);

        String[] fieldSplitter = value.split(JsonConstants.COMMA);
        if (fieldSplitter.length <= 0) {
            return mapperMap;
        }

        for (String stringEntry : fieldSplitter) {
            String[] entrySplitter = stringEntry.split(":");
            if (entrySplitter.length < 1) {
                continue;
            }
            mapperMap.put(getEntryKey(entrySplitter[0]), getEntryValue(entrySplitter[1]));
        }
        return mapperMap;
    }

    public static Object[] stringToJsonArray(@NonNull String value) {
        if (value.charAt(0) != '[') {
            throw new IllegalArgumentException("String value is not json object!");
        }

        String[] arraySplitter = value.split(JsonConstants.COMMA);
        if (arraySplitter.length <= 0) {
            return new Object[0];
        }

        int splitterArraySize = arraySplitter.length;
        Object[] array = new Object[splitterArraySize - 1];
        for (int i = 0; i < splitterArraySize; i++) {
            array[i] = getEntryValue(arraySplitter[i]);
        }
        return array;
    }

    private static Object getEntryValue(String value) {
        // In case of string
        if (value.contains(JsonConstants.QUOTATION)) {
            return value.replace(JsonConstants.QUOTATION, "");
        }

        // In case of boolean
        if (value.equals("true") || value.equals("false")) {
            return Boolean.valueOf(value);
        }

        // It's object again.
        if (value.startsWith("{")) {
            return new JsonObject(stringToJsonObject(value));
        }

        // It's array
        if (value.startsWith("[")) {
            return new JsonArray(stringToJsonArray(value));
        }

        return value;
    }

    private static String getEntryKey(String key) {
        return key.replace(JsonConstants.QUOTATION, "").trim();
    }
}