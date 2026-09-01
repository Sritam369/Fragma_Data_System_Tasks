package com.sri.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String phone;
    private String department;
    private String designation;
}