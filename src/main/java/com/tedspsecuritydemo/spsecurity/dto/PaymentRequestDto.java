package com.tedspsecuritydemo.spsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    @NotNull
    public int amount;

    public String pay_description;

    @NotNull
    public int company_id;
}
