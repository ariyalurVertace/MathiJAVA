package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="State", schema="State")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime shippingDate;
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    private Product Product;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;
}


