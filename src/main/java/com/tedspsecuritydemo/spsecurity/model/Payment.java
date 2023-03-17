package com.tedspsecuritydemo.spsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    private String referenceNumber;

    private int amount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "payment_user", joinColumns = @JoinColumn(name = "payment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> users;

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
