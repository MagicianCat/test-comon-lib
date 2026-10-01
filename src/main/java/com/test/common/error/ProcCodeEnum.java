package com.test.common.error;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

@Getter
public enum ProcCodeEnum {
    PROC_CODE_100S1000("100S1000", "Biz system trade success"),
    PROC_CODE_000F1002("000F1002", "Internal system exception"),
    PROC_CODE_000F2001("000F2001", "External syatem exception"),
    PROC_CODE_000F1003("000F1003", "Database exception"),
    PROC_CODE_800E0001("800E0001", "{} must not be null"),
    PROC_CODE_300E0001("300E0001", "{}");

    private final String code;
    private final String msg;
    protected static final Map<String, ProcCodeEnum> PROC_CODE_ENUM_MAP = Maps.newHashMap();

    ProcCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg(String code, Object... param) {
        ProcCodeEnum codeEnum = PROC_CODE_ENUM_MAP.get(code);
        return Objects.isNull(codeEnum) ? "" : String.format(codeEnum.getMsg(), param);
    }

    static {
        ProcCodeEnum[] values = values();
        for (ProcCodeEnum codeEnum : values) {
            PROC_CODE_ENUM_MAP.put(codeEnum.getCode(), codeEnum);
        }
    }
}
