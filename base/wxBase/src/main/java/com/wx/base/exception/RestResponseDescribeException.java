package com.wx.base.exception;

import lombok.Data;

/**
 * rest响应描述异常
 */
@Data
public class RestResponseDescribeException extends RuntimeException {
    private Integer code;

    /**
     * 继承exception，加入错误状态值
     *
     * @param codeEnum
     */
    public RestResponseDescribeException(RestResponseCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @param code
     */
    public RestResponseDescribeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 业务异常
     *
     * @param message
     */
    public RestResponseDescribeException(String message) {
        super(message);
        this.code = RestResponseCodeEnum.BUSINESS_ERROR.getCode();

    }
}
