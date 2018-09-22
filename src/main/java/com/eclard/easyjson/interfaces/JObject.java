package com.eclard.easyjson.interfaces;

/**
 * Created by Milan on 9/13/18.
 */
public interface JObject {
    JObject put(String key, Object object);
    String get(String key);
    boolean getBoolean(String key);
    int getInteger(String key);
    float getFloat(String key);
    double getDouble(String key);
    boolean contains(String key);
}