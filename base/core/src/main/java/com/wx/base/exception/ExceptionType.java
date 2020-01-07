package com.wx.base.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常类型顶层接口,推荐使用枚举来进行实现
 */
public interface ExceptionType {

    /**
     * 异常类型的数字编号
     */
    int getCode();

    /**
     * 异常类型的描述
     */
    String getDescription();

    default String descriptionWithArgs(Object[] args) {
        if (StringUtils.isNotBlank(this.getDescription())) {
            return String.format(this.getDescription(), args);
        }
        return this.getDescription();
    }

}
