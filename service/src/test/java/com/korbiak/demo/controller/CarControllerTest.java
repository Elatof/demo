package com.korbiak.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carServiceMock;

    @Autowired
    private ObjectMapper objMapper;

    private CarDto getMockCar() {
        return CarDto.builder()
                .id(1)
                .date(new Date())
                .engines(List.of(EngineDto.builder()
                        .id(1)
                        .build()))
                .company(CarCompanyDto.builder()
                        .id(1)
                        .build())
                .modelName("ModelName")
                .price(2345)
                .build();
    }

    private CarInputDto getMockInputCar() {
        return CarInputDto.builder()
                .date(new Date())
                .engines(List.of(EngineDto.builder()
                        .id(1)
                        .build()))
                .modelName("ModelName")
                .company(CarCompanyDto.builder()
                        .id(1)
                        .build())
                .price(2345)
                .build();
    }


    @Test
    public void getAllCarsTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.getAllCars()).thenReturn(List.of(carDto));

        mockMvc.perform(get("/demo-api/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(carDto.getId()))
                .andExpect(jsonPath("$.[0].modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.[0].price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.[0].engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void getCarByIdTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.getCarById(carDto.getId()))
                .thenReturn(carDto);

        mockMvc.perform(get("/demo-api/cars/{id}", carDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carDto.getId()))
                .andExpect(jsonPath("$.modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void getCarByNameTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.getCarByName(carDto.getModelName()))
                .thenReturn(carDto);

        mockMvc.perform(get("/demo-api/cars/modelName")
                .param("modelName", carDto.getModelName())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carDto.getId()))
                .andExpect(jsonPath("$.modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void getCarByCompanyIdsTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.getCarsByCompanyIds(List.of(1)))
                .thenReturn(List.of(carDto));

        mockMvc.perform(get("/demo-api/cars/companies/")
                .param("ids", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(carDto.getId()))
                .andExpect(jsonPath("$.[0].modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.[0].price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.[0].engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void getCarByEngineIdsTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.getCarsByEngineIds(List.of(1)))
                .thenReturn(List.of(carDto));

        mockMvc.perform(get("/demo-api/cars/engines/")
                .param("ids", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(carDto.getId()))
                .andExpect(jsonPath("$.[0].modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.[0].price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.[0].engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void updateCarTest() throws Exception {
        CarDto carDto = getMockCar();
        when(carServiceMock.updateCar(carDto))
                .thenReturn(carDto);

        mockMvc.perform(put("/demo-api/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(carDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carDto.getId()))
                .andExpect(jsonPath("$.modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void saveCarTest() throws Exception {
        CarDto carDto = getMockCar();
        CarInputDto carInputDto = getMockInputCar();
        when(carServiceMock.saveCar(carInputDto))
                .thenReturn(carDto);

        mockMvc.perform(post("/demo-api/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(carInputDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carDto.getId()))
                .andExpect(jsonPath("$.modelName").value(carDto.getModelName()))
                .andExpect(jsonPath("$.price").value(carDto.getPrice()))
                .andExpect(jsonPath("$.engines.length()").value(carDto.getEngines().size()));
    }

    @Test
    public void deleteCarTest() throws Exception {
        int testId = 666;
        doNothing().when(carServiceMock).deleteCar(testId);

        mockMvc.perform(delete("/demo-api/cars/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

}
