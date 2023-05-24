package com.tedspsecuritydemo.spsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = {
                CascadeType.PERSIST, CascadeType.MERGE
            },
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    },
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_payment",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<Payment> payments = new HashSet<>();

    public void addPayment(Payment u) {
        this.payments.add(u);
        u.getUsers().add(this);
    }

    public void removePayment(long tagId) {
        Payment payment = this.payments.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (payment != null) {
          this.payments.remove(payment);
          payment.getUsers().remove(this);
        }
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }


    public Users(Users users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.roles = users.getRoles();
        this.name = users.getName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

}
