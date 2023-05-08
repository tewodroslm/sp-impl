package com.tedspsecuritydemo.spsecurity.controller.controllerAdvice;

import com.tedspsecuritydemo.spsecurity.dto.responseWrapper.ErrorResponse;
import com.tedspsecuritydemo.spsecurity.dto.responseWrapper.IgnoreResponseBinding;
import com.tedspsecuritydemo.spsecurity.dto.responseWrapper.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class CustomResponseAdvise implements ResponseBodyAdvice<Object> {



    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("Inside Custom Response Advise");
        log.info(o.toString());
        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {

            if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseBinding.class) == false) {
                if ((!(o instanceof ErrorResponse)) && (!(o instanceof SuccessResponse))) {
                    SuccessResponse<Object> responseBody = new SuccessResponse<>(o);
                    return responseBody;
                }
            }
        }
        return o;
    }
}
