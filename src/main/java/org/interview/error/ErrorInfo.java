package org.interview.error;

public enum ErrorInfo {

    AUTHENTICATION_FAILED("Failed to authenticate twitter account", "1001");

    private final String errorMsg;
    private final String errorCode;

    ErrorInfo(String errorMsg, String errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
