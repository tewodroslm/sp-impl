package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PaymentActionRequestDto {

    private int userId;
    private String actionStatus;
    private int paymentId;
}
