package com.vertace.javapostgre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    public enum Status {
        SUCCESS, FAILED
    }

    private String status;
    private String message;

    public ApiResponse(String status) {
        this.status = status;
    }
}
