# :octocat: EasyJson
EasyJson is java/android module for easy json manipulation. It is just another fun project to kill time.
Please do not use it in production code, it is not tested :)

For production code i suggest Gson lib fully featured library:
[Gson](https://github.com/google/gson)

# How to use it?

### Object to JsonObject:

```
MockTestClass mockTestClass = new MockTestClass();
mockTestClass.setName("Milan");
mockTestClass.setAddress("Some great street");
mockTestClass.setStreet("Some stupid field, 22");
mockTestClass.setNumber(222);

JsonObject jsonObject = new JsonObject(mockTestClass);

```

### Json array:

```
JsonArray jsonArray = new JsonArray();
jsonArray.add("RedBull");
jsonArray.add("Ferrari");
```

For more, please take a look at tests :)
