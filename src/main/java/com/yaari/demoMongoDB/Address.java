package com.yaari.demoMongoDB;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private  String contry;
    private String city;
    private String postCode;
}
