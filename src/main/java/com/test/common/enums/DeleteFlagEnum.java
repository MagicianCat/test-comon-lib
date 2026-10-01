package com.test.common.enums;

import lombok.Getter;

public enum DeleteFlagEnum {
    NOT_DELETED("not deleted", (byte) 0),
    DELETED("deleted", (byte) 1);

    @Getter
    private final String detail;
    @Getter
    private final Byte dbValue;

    DeleteFlagEnum(final String detail, final Byte dbValue) {
        this.detail = detail;
        this.dbValue = dbValue;
    }
}
