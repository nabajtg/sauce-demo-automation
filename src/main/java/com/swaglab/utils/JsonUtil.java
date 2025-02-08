package com.swaglab.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    public static String readFile(String fileLoc){
        try {
            return Files.readString(Paths.get(System.getProperty("user.dir") + fileLoc));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
