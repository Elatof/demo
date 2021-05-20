package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.mapper.CarCompanyMapper;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.model.Car;
import com.korbiak.demo.model.CarCompany;
import com.korbiak.demo.model.Engine;
import com.korbiak.demo.repository.CarCompanyRepo;
import com.korbiak.demo.repository.CarRepo;
import com.korbiak.demo.repository.EngineRepo;
import com.korbiak.demo.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CarCompanyRepo companyRepo;

    @Mock
    private CarCompanyMapper companyMapper;

    @Mock
    private EngineRepo engineRepo;

    @Mock
    private CarRepo carRepo;

    private final CarCompany companyMock = CarCompany.builder()
            .id(1)
            .name("Name")
            .city("City")
            .country("Country")
            .cars(List.of(Car.builder()
                    .engines(List.of(new Engine()))
                    .build()))
            .build();

    private final CarCompanyDto companyDtoMock = CarCompanyDto.builder()
            .id(1)
            .name("Name")
            .city("City")
            .country("Country")
            .build();

    private final CarCompanyInputDto companyInputDtoMock = CarCompanyInputDto.builder()
            .name("Name")
            .city("City")
            .country("Country")
            .build();

    @BeforeEach
    public void initMockMethods() {
        when(companyMapper.getDtoFromModel(companyMock)).thenReturn(companyDtoMock);
    }

    @Test
    public void getAllCompaniesTest() {
        when(companyRepo.findAll()).thenReturn(List.of(companyMock));

        List<CarCompanyDto> resultTest = companyService.getAllCarCompanies();
        assertEquals(resultTest.get(0), companyDtoMock);
    }

    @Test
    public void getCompanyByIdTest() {
        int testId = 666;
        when(companyRepo.findById(testId)).thenReturn(Optional.of(companyMock));

        CarCompanyDto resultTest = companyService.getCarCompanyById(testId);
        assertEquals(resultTest, companyDtoMock);
    }


    @Test
    public void saveCarCompanyTest() {
        when(companyMapper.getModelFromDto(companyInputDtoMock)).thenReturn(companyMock);
        when(companyRepo.save(companyMock)).thenReturn(companyMock);

        CarCompanyDto resultTest = companyService.saveCarCompany(companyInputDtoMock);
        assertEquals(resultTest, companyDtoMock);
    }

    @Test
    public void updateCarCompanyTest() {
        when(companyMapper.getModelFromDto(companyDtoMock)).thenReturn(companyMock);
        when(companyRepo.save(companyMock)).thenReturn(companyMock);

        CarCompanyDto resultTest = companyService.updateCarCompany(companyDtoMock);
        assertEquals(resultTest, companyDtoMock);
    }

    @Test
    public void resetCarCompanyTest() {
        when(companyMapper.getModelFromDto(companyInputDtoMock))
                .thenReturn(companyMock);
        when(companyRepo.getCompanyByName(companyInputDtoMock.getName()))
                .thenReturn(companyMock);
        doNothing().when(engineRepo).delete(any(Engine.class));
        doNothing().when(carRepo).delete(any(Car.class));

        when(companyRepo.save(companyMock)).thenReturn(companyMock);

        CarCompanyDto resultTest = companyService.resetCarCompany(companyInputDtoMock);

        assertEquals(resultTest, companyDtoMock);
    }

    @Test
    public void resetCarCompanyNullCaseTest() {
        when(companyMapper.getModelFromDto(companyInputDtoMock))
                .thenReturn(companyMock);
        when(companyRepo.getCompanyByName(companyInputDtoMock.getName()))
                .thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> companyService.resetCarCompany(companyInputDtoMock));
    }

    @Test
    public void deleteCompanyTest() {
        int testId = 66;
        doNothing().when(companyRepo).deleteById(testId);
        companyService.deleteCarCompany(testId);
    }

    @Test
    public void deleteCompanyNotExistCaseTest() {
        int testId = 66;
        doThrow(new EmptyResultDataAccessException(1)).when(companyRepo).deleteById(testId);
        assertThrows(IllegalArgumentException.class, () -> companyService.deleteCarCompany(testId));
    }
}
