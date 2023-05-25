package com.lsw.management.common.exception;


import com.lsw.management.common.constants.ErrorCode;

/**
 * @author lsw
 * @Date 2023/4/3 17:56
 * @desc
 */
public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code=500;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
