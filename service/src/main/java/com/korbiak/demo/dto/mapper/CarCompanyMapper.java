package com.korbiak.demo.dto.mapper;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.model.CarCompany;

public interface CarCompanyMapper {

    CarCompanyDto getDtoFromModel(CarCompany company);

    CarCompany getModelFromDto(CarCompanyInputDto companyInputDto);

    CarCompany getModelFromDto(CarCompanyDto companyDto);
}
