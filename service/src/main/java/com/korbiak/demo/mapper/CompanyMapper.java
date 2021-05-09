package com.korbiak.demo.mapper;

import com.korbiak.demo.dto.input.CompanyInputDto;
import com.korbiak.demo.dto.output.CompanyDto;
import com.korbiak.demo.model.Company;
import org.mapstruct.Mapper;

public interface CompanyMapper {

    CompanyDto getDtoFromModel(Company company);

    Company getModelFromDto(CompanyInputDto companyInputDto);

    Company getModelFromDto(CompanyDto companyDto);
}
