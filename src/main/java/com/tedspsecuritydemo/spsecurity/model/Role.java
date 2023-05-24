package com.tedspsecuritydemo.spsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "roles")
    private Set<Users> users = new HashSet<>();
    
}
/*

@JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private Customer customer; */