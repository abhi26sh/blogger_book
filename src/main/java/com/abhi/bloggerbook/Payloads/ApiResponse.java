package com.abhi.bloggerbook.Payloads;

public class ApiResponse {
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
