package com.tedspsecuritydemo.spsecurity.repository;

import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(
           value ="SELECT * FROM payment",
            nativeQuery = true
    )
    List<Payment> findAllPayment();

    @Query(
            value = "SELECT * from payment where payee_id=:pId",
            nativeQuery = true
    )
    List<Payment> findMyPayments(@Param("pId") int uid);

    Payment findById(int id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE payment p set p.status = ?1 where p.id = ?2")
//    void updatePayment(int status,int paymentId);

}


//@Modifying
//@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    int paymentId;
//    String paymentDescription;
//
//    String status;
//    String referenceNumber;
//    int amount;