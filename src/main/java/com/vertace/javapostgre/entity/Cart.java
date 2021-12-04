package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="Cart", schema="Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String count;
    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.ALL)
    private UserProfile userProfile;
    private Product product;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;

}
