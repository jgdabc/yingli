package jgdabc.yingli.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private final  static  ObjectMapper  OBJECT_MAPPER =  new ObjectMapper();
//    将java对象转换为json

    public static String toJson(Object object){
        try {
            return  OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
//    将json转换为java对象
    public static <T> T toObject(String json,Class<T>tClass )
    {
        try {
            return OBJECT_MAPPER.readValue(json,tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
