package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto {

    int paymentId;
    String paymentDescription;

    String status;
    String referenceNumber;
    int amount;

}

/*
   when payment creation
    - pay description
    - company id
    - amount
    - user payee id

    - reference number 9 digit unique alphanumeric
    - status initially should be 'initiated'
*/