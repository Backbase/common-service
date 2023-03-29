package com.backbase.accelerators.common.util;

import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Component
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }


    public static String convertObjectToJson(Object obj) {
        try {
            mapper.findAndRegisterModules();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           return  mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new InternalServerErrorException(ex.getCause());
        }
    }


    public static Object readJsonfileToObject(String fileNameWithPath, Class type) throws IOException {
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(readJsonFile(fileNameWithPath), type);

    }

    private static BufferedReader readJsonFile(String fileNameWithPath) {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(JsonUtil.class.getClassLoader().getResourceAsStream(
                        fileNameWithPath))));
    }
}