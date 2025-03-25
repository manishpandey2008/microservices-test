package com.micro.user.service.extarnalService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
    public static String toJsonString(Object obj){
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e){
            log.debug("Error serializing {}",obj,e);
        }
        return null;
    }

    public static <T> T convertToClass(String response, Class<T> returnClass) {
        try {
            return convert(response, returnClass);
        } catch (Exception e) {
            log.error("Error converting {} to {}, {}", response, returnClass.getSimpleName(), ExceptionUtils.getRootCause(e).getMessage());
            return returnClass.cast(response);
        }
    }

    @SneakyThrows
    public static  <T> T convert(String string, Class<T> toObjectOf) {
        try {
            return JsonUtils.getObjectMapper().readValue(string, toObjectOf);
        } catch (JsonProcessingException e) {
            log.error("Error converting {},{}", string, ExceptionUtils.getRootCause(e).getMessage());
            try {
                return JsonUtils.getObjectMapper().convertValue(string, toObjectOf);
            } catch (Exception ex) {
                log.error("Error converting {}, {}, {}", string, string, ExceptionUtils.getRootCause(ex).getMessage());
                return JsonUtils.getObjectMapper().convertValue(getFromValue(string),toObjectOf);
            }
        }

    }

    @SneakyThrows
    private static Object getFromValue(String string){
        try {
            return JsonUtils.getObjectMapper().readValue(string, Map.class);
        } catch (JsonProcessingException e) {
            try{
                log.error("Error converting {} {} {}", string, string, ExceptionUtils.getRootCause(e).getMessage());
                return JsonUtils.getObjectMapper().readValue(string, List.class);
            }catch (Exception ex){
                throw new RuntimeException();
            }
        }
    }
}
