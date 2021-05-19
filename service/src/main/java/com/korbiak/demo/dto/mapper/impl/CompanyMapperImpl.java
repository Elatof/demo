package com.korbiak.demo.dto.mapper.impl;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.mapper.CarCompanyMapper;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.model.CarCompany;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements CarCompanyMapper {
    @Override
    public CarCompanyDto getDtoFromModel(CarCompany company) {
        if (company == null) {
            return new CarCompanyDto();
        }
        return CarCompanyDto.builder()
                .id(company.getId())
                .city(company.getCity())
                .country(company.getCountry())
                .name(company.getName())
                .build();
    }

    @Override
    public CarCompany getModelFromDto(CarCompanyInputDto companyInputDto) {
        if (companyInputDto == null) {
            return new CarCompany();
        }
        return CarCompany.builder()
                .city(companyInputDto.getCity())
                .name(companyInputDto.getName())
                .country(companyInputDto.getCountry())
                .build();
    }

    @Override
    public CarCompany getModelFromDto(CarCompanyDto companyDto) {
        if (companyDto == null) {
            return new CarCompany();
        }
        return CarCompany.builder()
                .id(companyDto.getId())
                .city(companyDto.getCity())
                .name(companyDto.getName())
                .country(companyDto.getCountry())
                .build();
    }
}
