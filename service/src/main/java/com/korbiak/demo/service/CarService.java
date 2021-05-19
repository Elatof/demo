package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.dto.output.EngineDto;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();

    List<CarDto> getCarsByCompanyIds(List<Integer> carCompanyIds);

    List<CarDto> getCarsByEngineIds(List<Integer> engineIds);

    CarDto getCarById(int id);

    CarDto getCarByName(String name);

    CarDto saveCar(CarInputDto carInputDto);

    CarDto updateCar(CarDto car);

    void deleteCar(int id);
}
