package com.korbiak.demo.controller;

import com.korbiak.demo.dto.input.CompanyInputDto;
import com.korbiak.demo.dto.output.CompanyDto;
import com.korbiak.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo-api/companies/")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "{companyId}")
    public CompanyDto getCompanyById(@PathVariable int companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PostMapping
    public CompanyDto saveNewCompany(@RequestBody @Validated CompanyInputDto companyInputDto) {
        return companyService.saveCompany(companyInputDto);
    }

    @PutMapping
    public CompanyDto updateCompany(@RequestBody @Validated CompanyDto inputDto) {
        return companyService.updateCompany(inputDto);
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable int companyId) {
        companyService.deleteCompany(companyId);
    }
}
