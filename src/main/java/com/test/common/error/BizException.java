package com.test.common.error;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BizException extends RuntimeException implements Serializable {
    private ErrorCode errorCode = new ErrorCode();

    public BizException(ErrorCode errorCode) {
        super(errorCode.toPrintString());
        this.errorCode = errorCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String msg) {
        this.errorCode.setCode(code);
        this.errorCode.setMsg(msg);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
