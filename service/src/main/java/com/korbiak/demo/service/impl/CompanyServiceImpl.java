package com.korbiak.demo.service.impl;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.mapper.CarCompanyMapper;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.model.CarCompany;
import com.korbiak.demo.model.Engine;
import com.korbiak.demo.repository.CarCompanyRepo;
import com.korbiak.demo.repository.CarRepo;
import com.korbiak.demo.repository.EngineRepo;
import com.korbiak.demo.service.CarCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CarCompanyService {

    private final CarCompanyRepo companyRepo;
    private final EngineRepo engineRepo;
    private final CarRepo carRepo;
    private final CarCompanyMapper companyMapper;

    @Override
    public List<CarCompanyDto> getAllCarCompanies() {
        List<CarCompany> companies = companyRepo.findAll();
        return companies.stream()
                .map(companyMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public CarCompanyDto getCarCompanyById(int id) {
        CarCompany company = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id = " + id));

        return companyMapper.getDtoFromModel(company);
    }

    @Override
    public CarCompanyDto saveCarCompany(CarCompanyInputDto companyInputDto) {
        CarCompany company = companyMapper.getModelFromDto(companyInputDto);
        companyRepo.save(company);

        return companyMapper.getDtoFromModel(company);
    }

    @Override
    public CarCompanyDto updateCarCompany(CarCompanyDto companyDto) {
        CarCompany company = companyMapper.getModelFromDto(companyDto);
        companyRepo.save(company);

        return companyMapper.getDtoFromModel(company);
    }

    //Update current version of car company to new with deleting companies cars and engines
    @Override
    @Transactional
    public CarCompanyDto resetCarCompany(CarCompanyInputDto companyInputDto) {
        CarCompany inputCompany = companyMapper.getModelFromDto(companyInputDto);
        CarCompany targetCompany = companyRepo.getCompanyByName(companyInputDto.getName());

        if (Objects.isNull(targetCompany)) {
            throw new IllegalArgumentException("Company with target name not found, rollback reset process");
        }
        List<Car> cars = targetCompany.getCars();
        for (Car car : cars) {
            List<Engine> engines = car.getEngines();
            for (Engine engine : engines) {
                engineRepo.delete(engine);
            }
            carRepo.delete(car);
        }
        inputCompany.setId(targetCompany.getId());
        companyRepo.save(inputCompany);

        return companyMapper.getDtoFromModel(inputCompany);
    }

    @Override
    public void deleteCarCompany(int id) {
        try {
            companyRepo.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("Company not exist with id = " + id);
        }
    }
}
