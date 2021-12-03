
package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="State", schema="State")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private float price;
    private int stock;
    private String Description;
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    //private Category category;
    
    //private Seller seller;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;

}


