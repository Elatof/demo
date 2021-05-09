package com.korbiak.demo.mapper.impl;

import com.korbiak.demo.dto.input.CompanyInputDto;
import com.korbiak.demo.dto.output.CompanyDto;
import com.korbiak.demo.mapper.CompanyMapper;
import com.korbiak.demo.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements CompanyMapper {
    @Override
    public CompanyDto getDtoFromModel(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .city(company.getCity())
                .country(company.getCountry())
                .name(company.getName())
                .build();
    }

    @Override
    public Company getModelFromDto(CompanyInputDto companyInputDto) {
        return Company.builder()
                .city(companyInputDto.getCity())
                .name(companyInputDto.getName())
                .country(companyInputDto.getCountry())
                .build();
    }

    @Override
    public Company getModelFromDto(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .city(companyDto.getCity())
                .name(companyDto.getName())
                .country(companyDto.getCountry())
                .build();
    }
}
