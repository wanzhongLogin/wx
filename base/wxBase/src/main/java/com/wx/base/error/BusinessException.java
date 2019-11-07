package com.wx.base.error;

public class BusinessException extends RuntimeException{

    private Error error;
    private String extMessage;

    public BusinessException(Error error) {
        super(error.getErrorCode() + ", " + error.getErrorMessage());
        this.extMessage = null;
        this.error = error;
    }

    public BusinessException(Error error, String extMessage) {
        super(error.getErrorCode() + ", " + error.getErrorMessage() + ", " + extMessage);
        this.extMessage = extMessage;
        this.error = error;
    }

    public BusinessException(final String errorCode, final String errorMessage, String extMessage) {
        super(errorCode + ", " + errorMessage + ", " + extMessage);
        this.extMessage = extMessage;
        this.error = new Error() {
            public String getNamespace() {
                return null;
            }

            public String getErrorCode() {
                return errorCode;
            }

            public String getErrorMessage() {
                return errorMessage;
            }
        };
    }

    public BusinessException(final String namespace, final String errorCode, final String errorMessage, String extMessage) {
        super(namespace + "." + errorCode + ", " + errorMessage + ", " + extMessage);
        this.extMessage = extMessage;
        this.error = new Error() {
            public String getNamespace() {
                return namespace;
            }

            public String getErrorCode() {
                return errorCode;
            }

            public String getErrorMessage() {
                return errorMessage;
            }
        };
    }

    public Error getError() {
        return this.error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public String toString() {
        return "ErrorCode : " + this.error.getErrorCode() + ", ErrorMessage : " + this.error.getErrorMessage() + ", ExtMessage : " + this.extMessage;
    }

}
