package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();

    CarDto getCarById(int id);

    CarDto saveCar(CarInputDto carInputDto);

    CarDto updateCar(CarDto car);

    void deleteCar(int id);
}
