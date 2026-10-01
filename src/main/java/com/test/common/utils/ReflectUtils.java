package com.test.common.utils;

import com.test.common.error.ProcCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    public static void setFieldValue(Field field, Object instance, Object value) {
        try {
            FieldUtils.writeField(field, instance, value, true);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] ReflectUtils set field value exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static Object getField(Field field, Object target) {
        try {
            return FieldUtils.readField(field, target, true);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] ReflectUtils get field value exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] ReflectUtils newInstance exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }

    public static Class<?> getClass(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (Exception e) {
            LOGGER.error("[Common-Lib] [FAIL] [DATA_ERROR] ReflectUtils get class by name exception: {}", e.getMessage(), e);
            throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_000F1002);
        }
    }
}
