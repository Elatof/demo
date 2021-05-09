package com.korbiak.client.controller;

import com.korbiak.client.model.Company;
import com.korbiak.client.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client-api/companies/")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping("reset")
    public Company resetCompany() {
        return companyService.resetCompany();
    }

}
