package com.korbiak.demo.mapper.impl;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.mapper.CarMapper;
import com.korbiak.demo.mapper.CompanyMapper;
import com.korbiak.demo.mapper.EngineMapper;
import com.korbiak.demo.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarMapperImpl implements CarMapper {

    private final CompanyMapper companyMapper;
    private final EngineMapper engineMapper;

    @Override
    public CarDto getDtoFromModel(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .company(companyMapper.getDtoFromModel(car.getCompany()))
                .date(car.getDate())
                .price(car.getPrice())
                .modelName(car.getModelName())
                .engines(car.getEngines().stream()
                        .map(engineMapper::getDtoFromModel)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Car getModelFromDto(CarInputDto carInputDto) {
        return Car.builder()
                .company(companyMapper.getModelFromDto(carInputDto.getCompany()))
                .date(carInputDto.getDate())
                .price(carInputDto.getPrice())
                .modelName(carInputDto.getModelName())
                .engines(carInputDto.getEngines().stream()
                        .map(engineMapper::getModelFromDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Car getModelFromDto(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .company(companyMapper.getModelFromDto(carDto.getCompany()))
                .date(carDto.getDate())
                .price(carDto.getPrice())
                .modelName(carDto.getModelName())
                .engines(carDto.getEngines().stream()
                        .map(engineMapper::getModelFromDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
