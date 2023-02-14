package com.tedspsecuritydemo.spsecurity.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role")
    private String role;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
/*

@JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private Customer customer; */