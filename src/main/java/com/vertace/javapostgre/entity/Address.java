package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="State", schema="State")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String addressLine1;
    private String addressLine2;
    private Integer pincode;
    private String landmark;
    @NotNull
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    private District district;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;
}
