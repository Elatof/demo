package com.korbiak.demo.controller;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo-api/cars/")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path = "{carId}")
    public CarDto getCarById(@PathVariable int carId) {
        return carService.getCarById(carId);
    }

    @PostMapping
    public CarDto saveNewCar(@RequestBody @Validated CarInputDto carInputDto) {
        return carService.saveCar(carInputDto);
    }

    @PutMapping
    public CarDto UpdateCar(@RequestBody @Validated CarDto inputDto) {
        return carService.updateCar(inputDto);
    }

    @DeleteMapping("{carId}")
    public void deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
    }
}
