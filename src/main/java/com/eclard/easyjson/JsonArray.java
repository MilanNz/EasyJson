package com.eclard.easyjson;

import com.eclard.easyjson.interfaces.JArray;
import com.eclard.easyjson.mapper.ObjectToJsonMapper;
import com.eclard.easyjson.mapper.StringToJsonMapper;

import java.util.Arrays;

/**
 * Created by Milan on 9/13/18.
 */
public class JsonArray implements JArray {
    private Object[] array;

    public JsonArray() {
        array = new Object[0];
    }

    public JsonArray(String value) {
        array = StringToJsonMapper.stringToJsonArray(value);
    }

    public JsonArray(Object[] objects) {
        array = objects;
    }

    @Override
    public void add(Object object) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = object;
    }

    @Override
    public void remove(int index) {
        //
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public String toString() {
        return ObjectToJsonMapper.arrayToString(array);
    }
}