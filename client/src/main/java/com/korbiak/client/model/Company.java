package com.korbiak.client.model;

import lombok.Data;

@Data
public class Company {
    private int id;
    private String name;
    private String country;
    private String city;
}
