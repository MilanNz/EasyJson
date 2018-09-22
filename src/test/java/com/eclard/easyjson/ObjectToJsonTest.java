package com.eclard.easyjson;

import com.eclard.easyjson.mapper.StringToJsonMapper;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Milan on 9/13/18.
 */
public class ObjectToJsonTest {

    @Test
    public void objectToJsonObjectTest() {
        String expectedOutput = "{\"number\":222,\"address\":\"Some great street\",\"name\":\"Milan\"}";

        MockTestClass mockTestClass = new MockTestClass();
        mockTestClass.setName("Milan");
        mockTestClass.setAddress("Some great street");
        mockTestClass.setStreet("Some stupid field, 22");
        mockTestClass.setNumber(222);

        JsonObject jsonObject = new JsonObject(mockTestClass);
        Assert.assertEquals(expectedOutput, jsonObject.toString());
        System.out.println(jsonObject.toString());
    }

    @Test
    public void objectToJsonObjectGetFieldTest() {
        MockTestClass mockTestClass = new MockTestClass();
        mockTestClass.setName("Milan");
        mockTestClass.setAddress("Some great street");
        mockTestClass.setStreet("Some stupid field, 22");
        mockTestClass.setNumber(222);

        JsonObject jsonObject = new JsonObject(mockTestClass);
        Assert.assertEquals(222, jsonObject.getInteger("number"));
        Assert.assertEquals("Milan", jsonObject.get("name"));
        Assert.assertNull(jsonObject.get("street"));
    }

    @Test
    public void arrayToStringTest() {
        String expectedArrayString = "[\"Milan\",\"Jovan\",\"Marko\"]";
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("Milan");
        jsonArray.add("Jovan");
        jsonArray.add("Marko");

        Assert.assertEquals(expectedArrayString, jsonArray.toString());
        Assert.assertEquals(3, jsonArray.size());
    }

    @Test
    public void arrayInJsonObjectToStringTest() {
        String expectedJsonObject = "{\"names\":[\"Milan\",\"Jovan\",\"Marko\",22,{\"number\":0,\"name\":\"nsnsnnsnsn\"}],\"address\":\"nesto\"}";

        MockTestClass mockTestClass = new MockTestClass();
        mockTestClass.setName("nsnsnnsnsn");
        mockTestClass.setStreet("Duciceva");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("Milan");
        jsonArray.add("Jovan");
        jsonArray.add("Marko");
        jsonArray.add(22);
        jsonArray.add(mockTestClass);

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("names", jsonArray);
        jsonObject.put("address", "nesto");

        Assert.assertEquals(expectedJsonObject, jsonObject.toString());
        System.out.println(jsonArray.toString());
    }

    @Test
    public void objectContainsArrayToStringTest() {
        MockTestClass mockTestClass = new MockTestClass();
        mockTestClass.setName("nsnsnnsnsn");
        mockTestClass.setArray(new String[] {"Milan", "Milan2"});

        // TODO: 9/22/18 Not working as expected!
        JsonObject jsonObject = new JsonObject(mockTestClass);
        System.out.println(jsonObject.toString());
    }

    @Test
    public void checkJsonObjectFieldTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("name", "MilanNz");
        Assert.assertTrue(jsonObject.contains("name"));
    }

    @Test
    public void stringToJsonObjectTest() {
        String stringJson = "{\"name\":\"Milan\", \"surename\":\"Adamovic\", \"flag\":false, \"number\":2}";
        JsonObject jsonObject = new JsonObject(stringJson);
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.getBoolean("flag"));
        System.out.println(jsonObject.getInteger("number"));
        System.out.println(jsonObject.getDouble("number"));
    }

    @Test
    public void stringToJsonMapperTest() {
        String stringJson = "{\"name\":\"Milan\", \"surename\":\"Adamovic\"}";
        Map<String, Object> objectMap = StringToJsonMapper.stringToJsonObject(stringJson);
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
