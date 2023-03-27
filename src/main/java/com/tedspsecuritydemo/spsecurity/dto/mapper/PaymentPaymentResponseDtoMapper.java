package com.tedspsecuritydemo.spsecurity.dto.mapper;

import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PaymentPaymentResponseDtoMapper {
    PaymentResponseDto paymentResponseDto(Payment payment);

    List<PaymentResponseDto> getAll(List<Payment> payments);
}
