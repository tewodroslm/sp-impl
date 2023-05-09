package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.PaymentActionRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // create a payment by User only
    @PostMapping("user/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDto paymentRequestDto){

        log.info("Inside Create payment : PaymentController");

        /*
        when payment creation
           - pay description
           - company id
           - amount
           - user payee id

           - reference number 9 digit unique alphanumeric
           - status initially should be 'initiated'
         */

        PaymentResponseDto paymentResponseDto = paymentService.initiatePayment(paymentRequestDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // retrieve all payment as a Manager
    @GetMapping("manager/get")
    @ResponseBody
    public ResponseEntity<?> getAllPayments(){
        log.info("Inside of Get All Payment -> Manger role");

        List<PaymentResponseDto> paymentResponseDto = paymentService.getAllPayment();

        return new ResponseEntity<List<PaymentResponseDto>>(paymentResponseDto, HttpStatus.OK);
    }


    @GetMapping("user/get")
    public ResponseEntity<?> getMyPayments(@RequestParam String userId){
        log.info("Inside of Get MY PaYMents");

        List<PaymentResponseDto> paymentResponseDtos = paymentService.getMyPayment(userId);
        log.info(paymentResponseDtos.toString());
        if(paymentResponseDtos == null) return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(paymentResponseDtos, HttpStatus.OK);
    }

    // Approve Payment(Manager Only
    // payment -> update payment status
    @PostMapping(value="manager/action",   produces = "application/json")
    public ResponseEntity<?> applyActionOnPayment(@RequestBody PaymentActionRequestDto paymentActionRequestDto){
        log.info("Inside applyActionOnPayment method");
        log.info(paymentActionRequestDto.toString());
        String sol = paymentService.applyActionMangerRo(paymentActionRequestDto.getActionStatus(), paymentActionRequestDto.getPaymentId());
        System.out.println("########### Sol is Not Impty ####### "+ sol);
        paymentActionRequestDto.setActionStatus(sol);
        return new ResponseEntity<>(paymentActionRequestDto, HttpStatus.OK);
    }


}






//+------------------+--------------+------+-----+---------+----------------+
//| Field            | Type         | Null | Key | Default | Extra          |
//+------------------+--------------+------+-----+---------+----------------+
//| payment_id       | int          | NO   | PRI | NULL    | auto_increment |
//| amount           | int          | NO   |     | NULL    |                |
//| pay_description  | varchar(255) | YES  |     | NULL    |                |
//| payee_id         | int          | NO   |     | NULL    |                |
//| reference_number | varchar(255) | YES  |     | NULL    |                |
//| status           | int          | NO   |     | NULL    |                |
//| company_id       | int          | NO   | MUL | NULL    |                |
//+------------------+--------------+------+-----+---------+----------------+