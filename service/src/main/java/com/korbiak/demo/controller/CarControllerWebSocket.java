package com.korbiak.demo.controller;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@SendTo("/topic/cars")
@RequiredArgsConstructor
public class CarControllerWebSocket {

    private final CarService carService;

    @MessageMapping("/cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @MessageMapping("/cars/{id}")
    public CarDto getCarById(@DestinationVariable int id) {
        return carService.getCarById(id);
    }

    @MessageMapping("/add-car")
    public CarDto saveNewCar(CarInputDto carInputDto) {
        return carService.saveCar(carInputDto);
    }

    @MessageMapping("/update-car")
    public CarDto updateCar(CarDto inputDto) {
        return carService.updateCar(inputDto);
    }

    @MessageMapping("/delete-cars/{id}")
    public String deleteCarById(@DestinationVariable int id) {
        carService.deleteCar(id);
        return "Deleted";
    }

}
