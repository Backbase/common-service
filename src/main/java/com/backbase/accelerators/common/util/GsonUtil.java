package com.backbase.accelerators.common.util;

import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class GsonUtil {
    private static final Gson gson = new Gson();

    private GsonUtil() {
    }

    public static String convertObjectToJson(Object obj){
        return new Gson().toJson(obj);
    }

    public static <T> T readJsonFileToObject(Type type, String fileNameWithPath) {
        return gson.fromJson(readJsonFile(fileNameWithPath), type);
    }

    public static <T> List<T> readJsonFileToObjectList(Type listType, String fileNameWithPath) {
        return (List)gson.fromJson(readJsonFile(fileNameWithPath), listType);
    }

    public static String readJsonFileToString(String fileNameWithPath) {
        try {
            return IOUtils.toString(readJsonFile(fileNameWithPath));
        } catch (IOException | NullPointerException var2) {
            throw new InternalServerErrorException(var2.getCause());
        }
    }

    private static BufferedReader readJsonFile(String fileNameWithPath) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(GsonUtil.class.getClassLoader().getResourceAsStream(fileNameWithPath))));
    }
}
