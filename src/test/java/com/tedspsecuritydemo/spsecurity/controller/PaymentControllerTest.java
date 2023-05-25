package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.PaymentActionRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import com.tedspsecuritydemo.spsecurity.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    PaymentService paymentService;

    @InjectMocks
    PaymentController paymentController;

    @Test
    void createPayment_Success() throws Exception{
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .amount(100)
                .company_id(2)
                .pay_description("payment added")
                .build();
        PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                .status("APPROVE")
                .referenceNumber("123AB")
                .amount(100)
                .pay_description("abc description")
                .build();

        Mockito.when(paymentService.initiatePayment(paymentRequestDto)).thenReturn(paymentResponseDto);
        ResponseEntity<?> responseEntity = paymentController.createPayment(paymentRequestDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }



    @Test
    void createPayment_Failure() throws Exception{
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .amount(100)
                .company_id(2)
                .pay_description("payment added")
                .build();

        Mockito.when(paymentService.initiatePayment(paymentRequestDto)).thenThrow(new Exception("message for unit test"));
        ResponseEntity<?> responseEntity = paymentController.createPayment(paymentRequestDto);
        assertEquals(304, responseEntity.getStatusCodeValue());
    }

    @Test
    void getAllPayments_Success(){
        List<PaymentResponseDto> paymentResponseDtoList = new ArrayList<>();
        Mockito.when(paymentService.getAllPayment()).thenReturn(paymentResponseDtoList);
        ResponseEntity<?> responseEntity = paymentController.getAllPayments();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getMyPayments_Success(){
        PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                .status("APPROVE")
                .referenceNumber("123AB")
                .amount(100)
                .pay_description("abc description")
                .build();

        List<PaymentResponseDto> paymentResponseDtoList = new ArrayList<>();
        paymentResponseDtoList.add(paymentResponseDto);
        Mockito.when(paymentService.getMyPayment(Mockito.anyString()))
                .thenReturn(paymentResponseDtoList);
        ResponseEntity<?> responseEntity = paymentController.getMyPayments("user12");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getMyPayments_Failure(){
        List<PaymentResponseDto> paymentResponseDtoList = new ArrayList<>();
        Mockito.when(paymentService.getMyPayment(Mockito.anyString()))
                .thenReturn(paymentResponseDtoList);
        ResponseEntity<?> responseEntity = paymentController.getMyPayments("user12");
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    void applyActionOnPayment_Success(){
        PaymentActionRequestDto paymentActionRequestDto = new PaymentActionRequestDto();
        paymentActionRequestDto.setActionStatus("INIT");
        paymentActionRequestDto.setPaymentId(4);
        paymentActionRequestDto.setUserId(1);
        ResponseEntity<?> responseEntity = paymentController.applyActionOnPayment(paymentActionRequestDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}