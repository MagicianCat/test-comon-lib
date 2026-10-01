package com.test.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorCode implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UNKNOWN = "9999";

    @NonNull
    private String code;
    @NonNull
    private String msg;

    public ErrorCode(@NonNull String code, @NonNull String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String toPrintString() {
        return code + ":" + msg;
    }
}
