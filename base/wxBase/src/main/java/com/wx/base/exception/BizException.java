package com.wx.base.exception;

public interface BizException {

    /**
     * 异常码
     */
    int getCode();

    /**
     * 异常描述
     */
    String getDescription();
}
