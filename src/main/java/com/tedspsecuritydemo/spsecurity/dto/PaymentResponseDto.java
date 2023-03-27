package com.tedspsecuritydemo.spsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    int id;
    String pay_description;

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