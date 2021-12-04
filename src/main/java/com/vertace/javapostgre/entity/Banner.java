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
// @Table (name="Banner", schema="Banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    @NotNull
    private String url;
    @NotNull
    private boolean isActive = false;
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;
}
