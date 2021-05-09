package com.korbiak.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "company")
public class CompanyExm {

    private String name;
    private String country;
    private String city;
}
