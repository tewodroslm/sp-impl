package com.tedspsecuritydemo.spsecurity.dto.responseWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tedspsecuritydemo.spsecurity.dto.responseWrapper.dto.ErrorDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T> {

    public ErrorResponse(T object, String message){
        error = new ErrorDTO<>(object, message);
    }

    public ErrorDTO<T> getError() {
        return error;
    }

    public void setError(ErrorDTO<T> error) {
        this.error = error;
    }

    private ErrorDTO<T> error;

}
