package com.ql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestJackson {

    public static void main(String[] args) throws IOException {
        //-----jackson----------
//        ObjectMapper mapper = new ObjectMapper();
////        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        TestData data = new TestData();
//        data.setAge(22);
//        data.setName("sdd");
//        //序列化
//        System.out.println("序列化:"+mapper.writeValueAsString(data));
//        //反序列化
//        String str = "{\"name\":\"sdd\",\"age\":22,\"other\":\"sth\"}";
//        System.out.println("序列化:"+mapper.readValue(str,TestData.class));


        //------fastjson------
//        TestData data = new TestData();
//        data.setAge(22);
//        data.setName("sdd");
//        System.out.println("序列化:"+JSON.toJSONString(data));
//        String str = "{\"name\":\"sdd\",\"age\":22,\"other\":\"sth\"}";
//        System.out.println("反序列化:"+JSONObject.parseObject(str,TestData.class));
    }


}
class TestData{

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
