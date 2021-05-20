package com.korbiak.demo.dto.mapper;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.mapper.impl.CarCompanyMapperImpl;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.model.CarCompany;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CarCompanyMapperTest {

    @InjectMocks
    private CarCompanyMapperImpl carCompanyMapper;

    private CarCompany getMockCarCompany() {
        return CarCompany.builder()
                .id(1)
                .name("Name")
                .city("City")
                .country("Country")
                .cars(List.of())
                .build();
    }

    private CarCompanyDto getMockCarCompanyDto() {
        return CarCompanyDto.builder()
                .id(1)
                .name("Name")
                .city("City")
                .country("Country")
                .build();
    }

    private CarCompanyInputDto getMockCarCompanyInputDto() {
        return CarCompanyInputDto.builder()
                .name("Name")
                .city("City")
                .country("Country")
                .build();
    }

    @Test
    public void getDtoFromModelTest() {
        CarCompany input = getMockCarCompany();

        CarCompanyDto testResult = carCompanyMapper.getDtoFromModel(input);
        assertEquals(testResult.getId(), input.getId());
        assertEquals(testResult.getName(), input.getName());
        assertEquals(testResult.getCity(), input.getCity());
        assertEquals(testResult.getCountry(), input.getCountry());
    }

    @Test
    public void getDtoFromModelNullCaseTest() {
        CarCompany input = null;

        CarCompanyDto testResult = carCompanyMapper.getDtoFromModel(input);
        assertEquals(testResult.getId(), 0);
        assertNull(testResult.getName());
        assertNull(testResult.getCity());
        assertNull(testResult.getCountry());
    }

    @Test
    public void getModelFromDtoTest() {
        CarCompanyInputDto input = getMockCarCompanyInputDto();

        CarCompany testResult = carCompanyMapper.getModelFromDto(input);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getName(), input.getName());
        assertEquals(testResult.getCity(), input.getCity());
        assertEquals(testResult.getCountry(), input.getCountry());
    }

    @Test
    public void getModelFromDtoNullCaseTest() {
        CarCompanyInputDto input = null;

        CarCompany testResult = carCompanyMapper.getModelFromDto(input);
        assertEquals(testResult.getId(), 0);
        assertNull(testResult.getName());
        assertNull(testResult.getCity());
        assertNull(testResult.getCountry());
    }

    @Test
    public void getModelFromDtoSecondCaseTest() {
        CarCompanyDto input = getMockCarCompanyDto();

        CarCompany testResult = carCompanyMapper.getModelFromDto(input);
        assertEquals(testResult.getId(), input.getId());
        assertEquals(testResult.getName(), input.getName());
        assertEquals(testResult.getCity(), input.getCity());
        assertEquals(testResult.getCountry(), input.getCountry());
    }

    @Test
    public void getModelFromDtoSecondNullCaseTest() {
        CarCompanyDto input = null;

        CarCompany testResult = carCompanyMapper.getModelFromDto(input);
        assertEquals(testResult.getId(), 0);
        assertNull(testResult.getName());
        assertNull(testResult.getCity());
        assertNull(testResult.getCountry());
    }
}
