package com.korbiak.demo.service.impl;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.mapper.CarMapper;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.repository.CarRepo;
import com.korbiak.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    private final CarMapper carMapper;

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepo.findAll();
        return cars.stream()
                .map(carMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsByCompanyIds(List<Integer> carCompanyIds) {
        List<Car> cars = carRepo.findAllByCompanyIdIn(carCompanyIds);

        return cars.stream()
                .map(carMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    public List<CarDto> getCarsByEngineIds(List<Integer> engineIds) {
        List<Car> cars = carRepo.findAllByEnginesIdIn(engineIds);

        return cars.stream()
                .map(carMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto getCarById(int id) {
        Car car = carRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id = " + id));

        return carMapper.getDtoFromModel(car);
    }

    @Override
    public CarDto getCarByName(String name) {
        Car car = carRepo.findCarByModelName(name);
        if (car == null) {
            throw new EntityNotFoundException("Car with not exist with name =" + name);
        }
        return carMapper.getDtoFromModel(car);
    }

    @Override
    public CarDto saveCar(CarInputDto carInputDto) {
        Car car = carMapper.getModelFromDto(carInputDto);
        carRepo.save(car);

        return carMapper.getDtoFromModel(car);
    }

    @Override
    public CarDto updateCar(CarDto carDto) {
        Car car = carMapper.getModelFromDto(carDto);
        carRepo.save(car);

        return carMapper.getDtoFromModel(car);
    }

    @Override
    public void deleteCar(int id) {
        try {
            carRepo.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("Car not exist with id = " + id);
        }
    }
}
