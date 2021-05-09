package com.korbiak.client.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korbiak.client.config.CompanyExm;
import com.korbiak.client.httpClient.ServiceClient;
import com.korbiak.client.model.Company;
import com.korbiak.client.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final ServiceClient client;
    private final CompanyExm input;

    @Override
    public Company resetCompany() {
        ObjectMapper mapper = new ObjectMapper();

        Company company = null;
        try {
            String response = client.resetCompany(mapper.writeValueAsString(input));
            company = mapper.readValue(response, Company.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return company;
    }
}
