package com.eclard.easyjson.interfaces;

/**
 * Created by Milan on 9/13/18.
 */
public interface JArray {
    void add(Object object);
    void remove(int index);
    Object get(int index);
    int size();
}