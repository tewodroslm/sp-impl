package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PaymentActionRequestDto {

    @NotNull
    private int userId;

    @NotBlank
    private String actionStatus;

    @NotNull
    private int paymentId;
}
