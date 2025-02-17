package com.swaglab.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

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

    public static void main(String[] args) {
        String json = readFile("CART001.json");
        System.out.println(convertJsonToPojo(json, CartData.class));
    }

}

@Data
class CartData {

    private List<String> items;
    
}

//     public static void main(String[] args) {
//         ObjectMapper objectMapper = new ObjectMapper();
//         String json = "{ \"name\": \"Jason\", \"age\": 30 }";

//         try {
//             objectMapper = new ObjectMapper();
//             CartPage cart
//             // System.out.println(person);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
