package com.vertace.javapostgre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileModel {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String address;

    private String userName;

    private String password;

    private boolean status;

    private String fullName;
    public void setFullName(){
        this.fullName = this.firstName + " " + this.lastName;
    }
}
