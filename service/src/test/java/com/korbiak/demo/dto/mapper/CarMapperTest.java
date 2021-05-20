package com.korbiak.demo.dto.mapper;


import com.korbiak.demo.dto.input.CarInputDto;
import com.korbiak.demo.dto.mapper.impl.CarMapperImpl;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.CarDto;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.model.CarCompany;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarMapperTest {

    @InjectMocks
    private CarMapperImpl carMapperImplTest;

    @Mock
    private CarCompanyMapper companyMapper;

    @Mock
    private EngineMapper engineMapper;

    private Car getMockCar() {
        return Car.builder()
                .id(1)
                .modelName("ModelName")
                .company(new CarCompany())
                .date(new Date())
                .engines(List.of())
                .price(1285)
                .build();
    }

    private CarDto getMockCarDto() {
        return CarDto.builder()
                .id(1)
                .modelName("ModelName")
                .company(new CarCompanyDto())
                .date(new Date())
                .engines(List.of())
                .price(1285)
                .build();
    }

    private CarInputDto getMockCarInputDto() {
        return CarInputDto.builder()
                .modelName("ModelName")
                .company(new CarCompanyDto())
                .date(new Date())
                .engines(List.of())
                .price(1285)
                .build();
    }

    @Test
    public void getDtoFromModelTest() {
        Car input = getMockCar();
        when(companyMapper.getDtoFromModel(Mockito.any(CarCompany.class)))
                .thenReturn(new CarCompanyDto());

        CarDto testResult = carMapperImplTest.getDtoFromModel(input);
        assertEquals(testResult.getId(), input.getId());
        assertEquals(testResult.getPrice(), input.getPrice());
        assertEquals(testResult.getDate(), input.getDate());
        assertEquals(testResult.getModelName(), input.getModelName());
        assertNotNull(testResult.getCompany());
    }

    @Test
    public void getDtoFromModelNullCaseTest() {
        CarDto testResult = carMapperImplTest.getDtoFromModel(null);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getPrice(), 0);
        assertNull(testResult.getDate());
        assertNull(testResult.getModelName());
        assertNull(testResult.getCompany());
    }

    @Test
    public void getModelFromDtoTest() {
        CarInputDto carInputDto = getMockCarInputDto();
        when(companyMapper.getModelFromDto(Mockito.any(CarCompanyDto.class)))
                .thenReturn(new CarCompany());

        Car testResult = carMapperImplTest.getModelFromDto(carInputDto);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getPrice(), carInputDto.getPrice());
        assertEquals(testResult.getDate(), carInputDto.getDate());
        assertEquals(testResult.getModelName(), carInputDto.getModelName());
        assertNotNull(testResult.getCompany());
    }

    @Test
    public void getModelFromDtoNullCaseTest() {
        CarInputDto carInputDto = null;
        Car testResult = carMapperImplTest.getModelFromDto(carInputDto);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getPrice(), 0);
        assertNull(testResult.getDate());
        assertNull(testResult.getModelName());
        assertNull(testResult.getCompany());
    }

    @Test
    public void getModelFromDtoSecondCaseTest() {
        CarDto carInputDto = getMockCarDto();
        when(companyMapper.getModelFromDto(Mockito.any(CarCompanyDto.class)))
                .thenReturn(new CarCompany());

        Car testResult = carMapperImplTest.getModelFromDto(carInputDto);
        assertEquals(testResult.getId(), carInputDto.getId());
        assertEquals(testResult.getPrice(), carInputDto.getPrice());
        assertEquals(testResult.getDate(), carInputDto.getDate());
        assertEquals(testResult.getModelName(), carInputDto.getModelName());
        assertNotNull(testResult.getCompany());
    }

    @Test
    public void getModelFromDtoNulSecondCaseTest() {
        CarDto carInputDto = null;
        Car testResult = carMapperImplTest.getModelFromDto(carInputDto);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getPrice(), 0);
        assertNull(testResult.getDate());
        assertNull(testResult.getModelName());
        assertNull(testResult.getCompany());
    }
}
