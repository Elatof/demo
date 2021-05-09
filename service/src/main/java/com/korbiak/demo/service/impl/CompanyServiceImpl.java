package com.korbiak.demo.service.impl;

import com.korbiak.demo.dto.input.CompanyInputDto;
import com.korbiak.demo.dto.output.CompanyDto;
import com.korbiak.demo.mapper.CompanyMapper;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.model.Company;
import com.korbiak.demo.repository.CompanyRepo;
import com.korbiak.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepo.findAll();
        return companies.stream()
                .map(companyMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(int id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id = " + id));

        return companyMapper.getDtoFromModel(company);
    }

    @Override
    public CompanyDto saveCompany(CompanyInputDto companyInputDto) {
        Company company = companyMapper.getModelFromDto(companyInputDto);
        companyRepo.save(company);

        return companyMapper.getDtoFromModel(company);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        Company company = companyMapper.getModelFromDto(companyDto);
        companyRepo.save(company);

        return companyMapper.getDtoFromModel(company);
    }

    @Override
    public void deleteCompany(int id) {
        if (companyRepo.existsById(id)){
            companyRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Company not exist with id = " + id);
        }
    }
}
