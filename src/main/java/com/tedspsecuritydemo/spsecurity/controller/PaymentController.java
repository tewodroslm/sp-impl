package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.PaymentRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // retrieve all payment


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