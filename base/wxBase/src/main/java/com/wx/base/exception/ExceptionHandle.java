package com.wx.base.exception;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.wx.base.exception.SoaExceptionFieldNames.ERROR_CODE;
import static com.wx.base.exception.SoaExceptionFieldNames.ERROR_MESSAGE;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {


    /**
     * BindException 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public void handleBindException(BindException e, HttpServletResponse response) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorList = new ArrayList<String>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorList.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        String message = StringUtils.join(errorList, ",") + ".";
        messageHandle(e, response, RestResponseCodeEnum.FIELD_VALIDTION_ERROR.getCode(), message);
    }

    /**
     * HystrixRuntimeException 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = HystrixRuntimeException.class)
    public void doException(HystrixRuntimeException e, HttpServletResponse response) {
        if (e.getFallbackException().getCause().getCause() instanceof RestResponseDescribeException) {
            doException((RestResponseDescribeException) e.getFallbackException().getCause().getCause(),response);
        }
        messageHandle(e, response,RestResponseCodeEnum.SERVICE_ERROR);
    }

    /**
     * 异常处理
     */
    @ExceptionHandler(value = ServerCallException.class)
    public void doException(ServerCallException e, HttpServletResponse response) {
        messageHandle(e, response, e.getCode(), e.getDescription());
    }

    /**
     * RuntimeException 异常处理
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public void doException(RuntimeException e, HttpServletResponse response) {
        messageHandle(e, response, RestResponseCodeEnum.DATA_ERROR);
    }

    /**
     * Exception 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void doException(Exception e, HttpServletResponse response) {
        messageHandle(e, response, RestResponseCodeEnum.UNKNOW_ERROR);
    }


    private void messageHandle(Exception e, HttpServletResponse response, RestResponseCodeEnum codeEnum) {
        messageHandle(e, response, codeEnum.getCode(), codeEnum.getMsg());
    }

    private void messageHandle(Exception e, HttpServletResponse response, int code, String message) {
        log.error("【{}】{}", message, e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.addHeader(ERROR_CODE, String.valueOf(code));
        response.addHeader(ERROR_MESSAGE, encodeString(message));
    }

    private String encodeString(String raw) {
        try {
            return new String(raw.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            return raw;
        }
    }

}
