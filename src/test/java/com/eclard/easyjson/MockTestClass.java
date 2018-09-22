package com.eclard.easyjson;

import com.eclard.easyjson.annotations.EasyFieldConfig;
import com.eclard.easyjson.annotations.EasyIgnoreField;

/**
 * Created by Milan on 9/13/18.
 */
public class MockTestClass {
    private String name;
    private String address;
    private int number;

    @EasyIgnoreField
    private String street;

    @EasyFieldConfig(name = "arrayName")
    private String[] array;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
