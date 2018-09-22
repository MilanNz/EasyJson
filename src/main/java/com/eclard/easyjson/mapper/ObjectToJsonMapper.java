package com.eclard.easyjson.mapper;

import android.support.annotation.NonNull;

import com.eclard.easyjson.annotations.EasyFieldConfig;
import com.eclard.easyjson.annotations.EasyIgnoreField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Created by Milan on 9/13/18.
 * - String -> JSON
 * - String -> Array
 *
 * - JSON -> String (done)
 * - ARRAY -> String (done)
 *
 * - Object -> Json (done)
 * - Object -> Array (done)
 */
public class ObjectToJsonMapper {

    private ObjectToJsonMapper() {}

    public static Map<String, Object> objectToJson(@NonNull Object object) {
        Map<String, Object> mapperMap = new HashMap<>();
        Class<?> objectClass = requireNonNull(object).getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            EasyIgnoreField easyIgnoreField = field.getAnnotation(EasyIgnoreField.class);
            if (easyIgnoreField != null) {
                continue;
            }

            EasyFieldConfig easyFieldConfig = field.getAnnotation(EasyFieldConfig.class);
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null) {
                    mapperMap.put(easyFieldConfig != null ? easyFieldConfig.name()
                            : field.getName(), fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mapperMap;
    }

    public static String jsonToString(@NonNull Map<String, Object> jsonMap) {
        return JsonToStringMapper.jsonToString(jsonMap);
    }

    public static String arrayToString(@NonNull Object[] array) {
        return JsonToStringMapper.arrayToString(array);
    }
}