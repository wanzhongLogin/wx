package com.wx.base.exception;


/**
 * Spring的事务针对这个及其子类异常，不会进行进行回滚。
 * 必须根据具体的业务类型来定义子类,不能直接使用这个类
 */
public abstract class CheckedException extends RuntimeException implements BizException {

    private static final long serialVersionUID = 5589536153777509425L;

    public CheckedException(ExceptionType type, String message) {
        super(message);
        this.code = type.getCode();
        this.description = type.getDescription();
    }

    public CheckedException(String message) {
        super(message);
        this.description = message;
    }

    public CheckedException(String message, Object... objs) {
        super(String.format(message, objs));
        this.description = String.format(message, objs);
    }

    public CheckedException(int exceptionCode, String message) {
        super(message);
        this.code = exceptionCode;
        this.description = message;
    }

    public CheckedException(int exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.code = exceptionCode;
        this.description = message;
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
        this.description = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private Integer code = StatusCodes.COMMON_INTERNAL_EXCEPTION;

    private String description;
}
