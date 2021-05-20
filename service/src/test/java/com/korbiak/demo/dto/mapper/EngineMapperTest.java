package com.korbiak.demo.dto.mapper;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.mapper.impl.EngineMapperImpl;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.model.Engine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class EngineMapperTest {
    @InjectMocks
    private EngineMapperImpl engineMapperImplTest;

    private Engine getMockEngine() {
        return Engine.builder()
                .id(1)
                .engineModel("EngineModel")
                .horsePower(150)
                .numberOfCylinders(4)
                .volume(2)
                .build();
    }

    private EngineDto getMockEngineDto() {
        return EngineDto.builder()
                .id(1)
                .engineModel("EngineModel")
                .horsePower(150)
                .numberOfCylinders(4)
                .volume(2)
                .build();
    }

    private EngineInputDto getMockEngineInputDto() {
        return EngineInputDto.builder()
                .engineModel("EngineModel")
                .horsePower(150)
                .numberOfCylinders(4)
                .volume(2)
                .build();
    }

    @Test
    public void getDtoFromModelTest() {
        Engine input = getMockEngine();

        EngineDto testResult = engineMapperImplTest.getDtoFromModel(input);
        assertEquals(testResult.getId(), input.getId());
        assertEquals(testResult.getEngineModel(), input.getEngineModel());
        assertEquals(testResult.getHorsePower(), input.getHorsePower());
        assertEquals(testResult.getNumberOfCylinders(), input.getNumberOfCylinders());
        assertEquals(testResult.getVolume(), input.getVolume());
    }

    @Test
    public void getDtoFromModelNullCaseTest() {
        EngineDto testResult = engineMapperImplTest.getDtoFromModel(null);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getNumberOfCylinders(), 0);
        assertEquals(testResult.getHorsePower(), 0);
        assertEquals(testResult.getVolume(), 0);
        assertNull(testResult.getEngineModel());
    }

    @Test
    public void getModelFromDtoTest() {
        EngineInputDto engineInputDto = getMockEngineInputDto();

        Engine testResult = engineMapperImplTest.getModelFromDto(engineInputDto);
        assertEquals(testResult.getEngineModel(), engineInputDto.getEngineModel());
        assertEquals(testResult.getHorsePower(), engineInputDto.getHorsePower());
        assertEquals(testResult.getNumberOfCylinders(), engineInputDto.getNumberOfCylinders());
        assertEquals(testResult.getVolume(), engineInputDto.getVolume());
    }

    @Test
    public void getModelFromDtoNullCaseTest() {
        EngineInputDto engineInputDto = null;
        Engine testResult = engineMapperImplTest.getModelFromDto(engineInputDto);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getNumberOfCylinders(), 0);
        assertEquals(testResult.getHorsePower(), 0);
        assertEquals(testResult.getVolume(), 0);
        assertNull(testResult.getEngineModel());
    }

    @Test
    public void getModelFromDtoSecondCaseTest() {
        EngineDto engineInputDto = getMockEngineDto();

        Engine testResult = engineMapperImplTest.getModelFromDto(engineInputDto);
        assertEquals(testResult.getId(), engineInputDto.getId());
        assertEquals(testResult.getEngineModel(), engineInputDto.getEngineModel());
        assertEquals(testResult.getHorsePower(), engineInputDto.getHorsePower());
        assertEquals(testResult.getNumberOfCylinders(), engineInputDto.getNumberOfCylinders());
        assertEquals(testResult.getVolume(), engineInputDto.getVolume());
    }

    @Test
    public void getModelFromDtoNulSecondCaseTest() {
        EngineDto engineInputDto = null;
        Engine testResult = engineMapperImplTest.getModelFromDto(engineInputDto);
        assertEquals(testResult.getId(), 0);
        assertEquals(testResult.getNumberOfCylinders(), 0);
        assertEquals(testResult.getHorsePower(), 0);
        assertEquals(testResult.getVolume(), 0);
        assertNull(testResult.getEngineModel());
    }
}
