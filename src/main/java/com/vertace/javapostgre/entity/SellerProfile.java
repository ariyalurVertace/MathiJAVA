package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import javax.mail.Address;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="SellerProfile", schema="SellerProfile")
public class SellerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @NotNull
    private String lastName;
    private String email;
    private String phoneNumber;
    @NotNull
    private boolean isApproved = false;
    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.ALL)
    private Address address;
    @NotNull
    //private UserId userId;
    @ColumnDefault("false")
    private Boolean isDeleted = false;
}
