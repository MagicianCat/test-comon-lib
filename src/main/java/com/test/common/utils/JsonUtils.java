package com.test.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.test.common.error.ProcCodeEnum;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@NoArgsConstructor
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapperNormal;
    private static final ObjectMapper mapperCamelToSnake;

    static {
        mapperNormal = new JsonMapper();
        mapperNormal.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperNormal.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    static {
        mapperCamelToSnake = new JsonMapper();
        mapperCamelToSnake.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperCamelToSnake.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapperCamelToSnake.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static String format(Object object) {
        try {
            return mapperNormal.writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] JsonUtils format exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static String formatCamelToSnake(Object object) {
        try {
            return mapperCamelToSnake.writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] JsonUtils format camel to snake exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return mapperNormal.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] JsonUtils parse exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static <T> T parseSnakeToCamel(String json, Class<T> clazz) {
        try {
            return mapperCamelToSnake.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] JsonUtils parse snake to camel exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }
}
