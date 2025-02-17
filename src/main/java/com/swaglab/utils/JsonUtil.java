package com.swaglab.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String readFile(String fileLoc){
        try {
            String fileLocFinal = fileLoc.contains(".json") ? fileLoc : fileLoc + ".json";
            return Files.readString(Paths.get(System.getProperty("user.dir") + fileLocFinal));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T convertJsonToPojo(String json, Class<T> cls){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            T object = objectMapper.readValue(json, cls);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


