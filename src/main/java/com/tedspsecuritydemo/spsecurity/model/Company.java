package com.tedspsecuritydemo.spsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Company parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Company> children = new HashSet<>();

   public Company(String companyName, Company parent){
       this.companyName = companyName;
       this.parent = parent;
   }

   public Company(String companyName){
       this.companyName = companyName;
   }
   public Company(){}

    public void addChild(Company child){
       this.children.add(child);
    }
}














