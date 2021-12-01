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
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    private State state;
    @NotNull
    @ColumnDefault("false")
    private Boolean isDeleted = false;

}
