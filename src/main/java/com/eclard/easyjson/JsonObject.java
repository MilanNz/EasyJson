package com.eclard.easyjson;

import com.eclard.easyjson.interfaces.JObject;
import com.eclard.easyjson.mapper.ObjectToJsonMapper;
import com.eclard.easyjson.mapper.StringToJsonMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Milan on 9/13/18.
 */
public class JsonObject implements JObject {
    private Map<String, Object> mapJson;

    public JsonObject(Map<String, Object> mapJson) {
        this.mapJson = mapJson;
    }

    public JsonObject(String value) {
        mapJson = StringToJsonMapper.stringToJsonObject(value);
    }

    public JsonObject(Object object) {
        mapJson = ObjectToJsonMapper.objectToJson(object);
    }

    public JsonObject() {
        mapJson = new HashMap<>();
    }

    @Override
    public JObject put(String key, Object object) {
        mapJson.put(key, object);
        return this;
    }

    @Override
    public String get(String key) {
        return (String) mapJson.get(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return (boolean) mapJson.get(key);
    }

    @Override
    public int getInteger(String key) {
        return (int) mapJson.get(key);
    }

    @Override
    public float getFloat(String key) {
        return (float) mapJson.get(key);
    }

    @Override
    public double getDouble(String key) {
        return (double) mapJson.get(key);
    }

    @Override
    public boolean contains(String key) {
        return mapJson != null && mapJson.containsKey(key);
    }

    @Override
    public String toString() {
        return ObjectToJsonMapper.jsonToString(mapJson);
    }
}