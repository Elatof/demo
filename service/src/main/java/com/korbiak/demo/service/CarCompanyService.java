package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;

import java.util.List;

public interface CarCompanyService {
    List<CarCompanyDto> getAllCarCompanies();

    CarCompanyDto getCarCompanyById(int id);

    CarCompanyDto saveCarCompany(CarCompanyInputDto companyInputDto);

    CarCompanyDto updateCarCompany(CarCompanyDto companyDto);

    CarCompanyDto resetCarCompany(CarCompanyInputDto companyInputDto);

    void deleteCarCompany(int id);
}
