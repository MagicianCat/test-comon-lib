package com.test.common.utils;

import com.test.common.error.ProcCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RiskObjectUtils {
    public static void validateNotBlank(String value, String name) {
        if (StringUtils.isBlank(value)) {
            throwBizExceptionWith800E0001(name);
        }
    }

    public static void validateNotNull(Object object, String name) {
        if (Objects.isNull(object)) {
            throwBizExceptionWith800E0001(name);
        }
    }

    public static void validateNotEmpty(Collection<?> collection, String name) {
        if (CollectionUtils.isEmpty(collection)) {
            throwBizExceptionWith800E0001(name);
        }
    }

    private static void throwBizExceptionWith800E0001(String name) {
        throw ExceptionUtils.buildBizException(ProcCodeEnum.PROC_CODE_800E0001, name);
    }
}
