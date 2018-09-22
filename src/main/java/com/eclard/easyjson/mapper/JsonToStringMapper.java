package com.eclard.easyjson.mapper;

import android.support.annotation.NonNull;

import com.eclard.easyjson.JsonArray;
import com.eclard.easyjson.JsonConstants;
import com.eclard.easyjson.JsonObject;

import java.util.Map;

/**
 * Created by Milan on 9/13/18.
 */
public class JsonToStringMapper {

    private JsonToStringMapper() {}

    public static String jsonToString(@NonNull Map<String, Object> jsonMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JsonConstants.OPEN_OBJECT_BRACKET);

        int counter = 0;
        int mapSize = jsonMap.size();
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            stringBuilder.append(JsonConstants.QUOTATION)
                    .append(entry.getKey())
                    .append(JsonConstants.QUOTATION);
            stringBuilder.append(":");
            stringBuilder.append(isArray(entry.getValue()) ? getStringArray(entry.getValue())
                    : getValue(entry.getValue()));

            if (counter != mapSize - 1)
                stringBuilder.append(",");

            counter++;
        }

        stringBuilder.append(JsonConstants.CLOSED_OBJECT_BRACKET);
        return stringBuilder.toString();
    }

    public static String arrayToString(Object[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(JsonConstants.OPEN_ARRAY_BRACKET);
        int size = array.length;
        for (int i = 0; i < size; i++) {
            sb.append(getValue(array[i]));
            if (i != size - 1)
                sb.append(",");
        }
        sb.append(JsonConstants.CLOSED_ARRAY_BRACKET);
        return sb.toString();
    }

    private static String getStringArray(Object object) {
        JsonArray jsonArray = (JsonArray) object;
        return jsonArray.toString();
    }

    private static boolean isArray(Object object) {
        return object instanceof JsonArray;
    }

    private static String getValue(Object objectValue) {
        if (objectValue instanceof String) {
            return JsonConstants.QUOTATION + objectValue + JsonConstants.QUOTATION;
        } else if (!isKnownType(objectValue)) {
            return new JsonObject(ObjectToJsonMapper.objectToJson(objectValue)).toString();
        }
        return String.valueOf(objectValue);
    }

    private static boolean isKnownType(Object object) {
        return object instanceof Boolean
                || object instanceof Character
                || object instanceof Byte
                || object instanceof Short
                || object instanceof Integer
                || object instanceof Long
                || object instanceof Float
                || object instanceof Double;
    }
}
