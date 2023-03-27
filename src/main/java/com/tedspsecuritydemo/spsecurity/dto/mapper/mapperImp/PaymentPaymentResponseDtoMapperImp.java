package com.tedspsecuritydemo.spsecurity.dto.mapper.mapperImp;

import com.tedspsecuritydemo.spsecurity.config.StatusEnum;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.dto.mapper.PaymentPaymentResponseDtoMapper;
import com.tedspsecuritydemo.spsecurity.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentPaymentResponseDtoMapperImp implements PaymentPaymentResponseDtoMapper {

    @Override
    public PaymentResponseDto paymentResponseDto(Payment payment) {
        if(payment == null) return null;
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        paymentResponseDto.setId(payment.getId());
        paymentResponseDto.setPay_description(payment.getPay_description());
        paymentResponseDto.setAmount(payment.getAmount());
        paymentResponseDto.setReferenceNumber(payment.getReferenceNumber());

        paymentResponseDto.setStatus(String.valueOf(payment.getStatus()));
        return paymentResponseDto;
    }

    @Override
    public List<PaymentResponseDto> getAll(List<Payment> payment){
        System.out.println("******* Inside get All ********");
        if(payment == null) return null;

        List<PaymentResponseDto> paymentResponseDtos = new ArrayList<>();
        for(Payment p: payment){
            PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

            paymentResponseDto.setId(p.getId());
            paymentResponseDto.setPay_description(p.getPay_description());
            paymentResponseDto.setAmount(p.getAmount());
            paymentResponseDto.setReferenceNumber(p.getReferenceNumber());

            paymentResponseDto.setStatus(String.valueOf(p.getStatus()));
            paymentResponseDtos.add(paymentResponseDto);
        }
        return paymentResponseDtos;
    }

}
