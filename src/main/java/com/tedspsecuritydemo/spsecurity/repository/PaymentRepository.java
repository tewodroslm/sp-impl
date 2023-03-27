package com.tedspsecuritydemo.spsecurity.repository;

import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(
           value ="SELECT * FROM payment",
            nativeQuery = true
    )
    List<Payment> findAllPayment();


}


//
//    int paymentId;
//    String paymentDescription;
//
//    String status;
//    String referenceNumber;
//    int amount;