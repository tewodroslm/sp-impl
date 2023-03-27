package com.tedspsecuritydemo.spsecurity.model;


import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Builder
@Data
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "payment_user", joinColumns = @JoinColumn(name = "payment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> users;

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

//package net.codejava;
//
//import java.util.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table
//public class Category {
//    @Id
//    @Column(name = "category_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(length = 64)
//    private String name;
//
//    @OneToOne
//    @JoinColumn(name = "parent_id")
//    private Category parent;
//
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//    private Set<Category> children = new HashSet<>();
//
//    public Category(String name, Category parent) {
//        this.name = name;
//        this.parent = parent;
//    }
//
//    public Category(String name) {
//        this.name = name;
//    }
//
//    public Category() {
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Category getParent() {
//        return parent;
//    }
//
//    public void setParent(Category parent) {
//        this.parent = parent;
//    }
//
//    public Set<Category> getChildren() {
//        return children;
//    }
//
//    public void setChildren(Set<Category> children) {
//        this.children = children;
//    }
//
//    public void addChild(Category children) {
//        this.children.add(children);
//    }
//}












