package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CompanyInputDto;
import com.korbiak.demo.dto.output.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(int id);

    CompanyDto saveCompany(CompanyInputDto companyInputDto);

    CompanyDto updateCompany(CompanyDto companyDto);

    void deleteCompany(int id);
}
