package com.vertace.javapostgre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// @Table (name="userProfile", schema="userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

}
