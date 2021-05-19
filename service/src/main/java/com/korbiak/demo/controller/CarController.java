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

    @GetMapping("companies")
    public List<CarDto> getCarsByCompanyIds(@RequestParam("ids") List<Integer> companyIds) {
        return carService.getCarsByCompanyIds(companyIds);
    }

    @GetMapping("engines")
    public List<CarDto> getCarsByEngineIds(@RequestParam("ids") List<Integer> engineIds) {
        return carService.getCarsByEngineIds(engineIds);
    }

    @GetMapping("{carId}")
    public CarDto getCarById(@PathVariable int carId) {
        return carService.getCarById(carId);
    }

    @PostMapping
    public CarDto saveNewCar(@RequestBody @Validated CarInputDto carInputDto) {
        return carService.saveCar(carInputDto);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody @Validated CarDto inputDto) {
        return carService.updateCar(inputDto);
    }

    @DeleteMapping("{carId}")
    public void deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
    }
}
