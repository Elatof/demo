package com.korbiak.demo.mapper;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.model.Car;
import org.mapstruct.Mapper;

public interface CarMapper {

    CarDto getDtoFromModel(Car car);

    Car getModelFromDto(CarInputDto carInputDto);

    Car getModelFromDto(CarDto carDto);
}
