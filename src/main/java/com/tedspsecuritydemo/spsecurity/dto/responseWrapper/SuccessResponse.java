package com.tedspsecuritydemo.spsecurity.dto.responseWrapper;

import com.tedspsecuritydemo.spsecurity.dto.responseWrapper.dto.SuccessDTO;

public class SuccessResponse<T> {

    private SuccessDTO<T> response;

    public SuccessDTO<T> getResponse() {
        return response;
    }

    public void setResponse(SuccessDTO<T> response) {
        this.response = response;
    }

    public SuccessResponse(T object) {
        this.response = new SuccessDTO<>(object);
    }

    public SuccessResponse(T object, String message) {
        this.response = new SuccessDTO<>(object, message);
    }

    public SuccessResponse(T object, Integer length, String message) {
        this.response = new SuccessDTO<>(object, length, message);
    }

    public SuccessResponse(T object, Integer length) {
        this.response = new SuccessDTO<>(object, length);
    }
}
