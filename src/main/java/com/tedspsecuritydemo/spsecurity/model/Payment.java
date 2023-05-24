package com.tedspsecuritydemo.spsecurity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    private String referenceNumber;

    private int amount;

    @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
              CascadeType.PERSIST,
              CascadeType.MERGE
          },
          mappedBy = "payments")
     @JsonIgnore
     private Set<Users> users = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private int status;

    private int payeeId;

    private String pay_description;

}


/**

    // payment
        // rNumber --> string;
        // amount  --> integer;
        // List of Approvers (managers)---> based on amount it will be given to manager to review payment
        // status  ---> 'on hold', 'canceled', 'approved'
        // Payee(user who is paying)
        // Payment report <List of>


    // ROLE
        // user
              // can create a payment
              // can see their own payment
              // user can cancel their payment
        // manager
             // can see every payment
             // can approve the paym
             // can hold the payment
             // can cancel the payment
        // admin
             // can not see the payment
             // can see the payment-report
             // handle the User and Company ...


    // Report
        // payment report ...

 */
