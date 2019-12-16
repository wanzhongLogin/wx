package com.wx.base.exception;

/**
 * Spring的事务针对这个及其子类异常，不会进行进行回滚。
 */
public class BizBusinessException extends BizCheckedException {

    private static final long serialVersionUID = 4984288360963210909L;

    public BizBusinessException(ExceptionType type, String message) {
        super(type, message);
    }

    public BizBusinessException(ExceptionType type) {
        this(type,type.getDescription());
    }

    public BizBusinessException(String message) {
        super(message);
    }

    public BizBusinessException(String message, Object... objs) {
        super(message, objs);
    }

    public BizBusinessException(int exceptionCode, String message) {
        super(exceptionCode, message);
    }

    public BizBusinessException(int exceptionCode, String message, Throwable cause) {
        super(exceptionCode, message, cause);
    }

    public BizBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
