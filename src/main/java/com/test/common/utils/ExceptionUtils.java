package com.test.common.utils;

import com.test.common.error.BizException;
import com.test.common.error.ErrorCode;
import com.test.common.error.ProcCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {
    public static BizException buildBizException(String msg) {
        return buildBizException(ProcCodeEnum.PROC_CODE_300E0001, msg);
    }

    public static BizException buildBizException(ProcCodeEnum procCodeEnum) {
        return new BizException(
                new ErrorCode(
                        procCodeEnum.getCode(),
                        procCodeEnum.getMsg()
                )
        );
    }

    public static BizException buildBizException(Exception e) {
        return buildBizException(ProcCodeEnum.PROC_CODE_000F1002, e);
    }

    public static BizException buildBizException(ProcCodeEnum procCodeEnum, Exception exception) {
        BizException bizException = new BizException(exception);
        bizException.setErrorCode(
                new ErrorCode(
                        procCodeEnum.getCode(),
                        procCodeEnum.getMsg()
                )
        );

        return bizException;
    }

    public static BizException buildBizException(ProcCodeEnum procCodeEnum, Object... args) {
        return new BizException(
                new ErrorCode(
                        procCodeEnum.getCode(),
                        getMsg(procCodeEnum, args)
                )
        );
    }

    private static String getMsg(ProcCodeEnum procCodeEnum, Object... args) {
        return ArrayUtils.isEmpty(args) ? procCodeEnum.getMsg() : procCodeEnum.getMsg(procCodeEnum.getCode(), args);
    }
}
