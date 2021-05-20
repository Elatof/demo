package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.mapper.CarMapper;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.model.CarCompany;
import com.korbiak.demo.repository.CarRepo;
import com.korbiak.demo.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepo carRepo;

    @Mock
    private CarMapper carMapper;

    private final Car carMock = Car.builder()
            .id(1)
            .company(new CarCompany())
            .price(1500)
            .date(new Date())
            .engines(List.of())
            .build();


    private final CarDto carDtoMock = CarDto.builder()
            .id(1)
            .company(new CarCompanyDto())
            .price(1500)
            .date(new Date())
            .engines(List.of())
            .build();

    private final CarInputDto carInputDtoMock = CarInputDto.builder()
            .company(new CarCompanyDto())
            .price(1500)
            .date(new Date())
            .engines(List.of())
            .build();

    @BeforeEach
    public void initMockMethods() {
        when(carMapper.getDtoFromModel(carMock)).thenReturn(carDtoMock);
    }

    @Test
    public void getAllCarsTest() {
        when(carRepo.findAll()).thenReturn(List.of(carMock));

        List<CarDto> resultTest = carService.getAllCars();
        assertEquals(resultTest.get(0), carDtoMock);
    }

    @Test
    public void getCarsByCompanyIdsTest() {
        List<Integer> companyIds = List.of(1);
        when(carRepo.findAllByCompanyIdIn(companyIds))
                .thenReturn(List.of(carMock));

        List<CarDto> resultTest = carService.getCarsByCompanyIds(companyIds);
        assertEquals(resultTest.get(0), carDtoMock);
    }

    @Test
    public void getCarsByEngineIdsTest() {
        List<Integer> engineIds = List.of(1);
        when(carRepo.findAllByEnginesIdIn(engineIds))
                .thenReturn(List.of(carMock));

        List<CarDto> resultTest = carService.getCarsByEngineIds(engineIds);
        assertEquals(resultTest.get(0), carDtoMock);
    }

    @Test
    public void getCarByIdTest() {
        int testId = 666;
        when(carRepo.findById(testId)).thenReturn(Optional.of(carMock));

        CarDto resultTest = carService.getCarById(testId);
        assertEquals(resultTest, carDtoMock);
    }

    @Test
    public void getCarByNameTest() {
        String testName = "testName";
        when(carRepo.findCarByModelName(testName)).thenReturn(carMock);

        CarDto resultTest = carService.getCarByName(testName);
        assertEquals(resultTest, carDtoMock);
    }

    @Test
    public void getCarByNameNullCaseTest() {
        String testName = "testName";
        when(carRepo.findCarByModelName(testName)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> carService.getCarByName(testName));
    }

    @Test
    public void saveCatTest() {
        when(carMapper.getModelFromDto(carInputDtoMock)).thenReturn(carMock);
        when(carRepo.save(carMock)).thenReturn(carMock);

        CarDto resultTest = carService.saveCar(carInputDtoMock);
        assertEquals(resultTest, carDtoMock);
    }

    @Test
    public void updateCatTest() {
        when(carMapper.getModelFromDto(carDtoMock)).thenReturn(carMock);
        when(carRepo.save(carMock)).thenReturn(carMock);

        CarDto resultTest = carService.updateCar(carDtoMock);
        assertEquals(resultTest, carDtoMock);
    }

    @Test
    public void deleteCarTest() {
        int testId = 66;
        doNothing().when(carRepo).deleteById(testId);
        carService.deleteCar(testId);
    }

    @Test
    public void deleteCarNotExistCaseTest() {
        int testId = 66;
        doThrow(new EmptyResultDataAccessException(1)).when(carRepo).deleteById(testId);
        assertThrows(IllegalArgumentException.class, () -> carService.deleteCar(testId));
    }
}
