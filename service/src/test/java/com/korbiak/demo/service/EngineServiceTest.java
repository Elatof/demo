package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.mapper.EngineMapper;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.model.Engine;
import com.korbiak.demo.repository.EngineRepo;
import com.korbiak.demo.service.impl.EngineServiceImpl;
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
public class EngineServiceTest {

    @InjectMocks
    private EngineServiceImpl engineService;

    @Mock
    private EngineRepo engineRepo;

    @Mock
    private EngineMapper engineMapper;

    private final Engine engineMock = Engine.builder()
            .id(1)
            .volume(2)
            .numberOfCylinders(4)
            .engineModel("EngineModel")
            .horsePower(150)
            .build();


    private final EngineDto engineDtoMock = EngineDto.builder()
            .id(1)
            .volume(2)
            .numberOfCylinders(4)
            .engineModel("EngineModel")
            .horsePower(150)
            .build();

    private final EngineInputDto engineInputDtoMock = EngineInputDto.builder()
            .volume(2)
            .numberOfCylinders(4)
            .engineModel("EngineModel")
            .horsePower(150)
            .build();

    @BeforeEach
    public void initMockMethods() {
        when(engineMapper.getDtoFromModel(engineMock)).thenReturn(engineDtoMock);
    }

    @Test
    public void getAllEnginesTest() {
        when(engineRepo.findAll()).thenReturn(List.of(engineMock));

        List<EngineDto> resultTest = engineService.getAllEngines();
        assertEquals(resultTest.get(0), engineDtoMock);
    }


    @Test
    public void getEngineByIdTest() {
        int testId = 666;
        when(engineRepo.findById(testId)).thenReturn(Optional.of(engineMock));

        EngineDto resultTest = engineService.getEngineById(testId);
        assertEquals(resultTest, engineDtoMock);
    }

    @Test
    public void saveEngineTest() {
        when(engineMapper.getModelFromDto(engineInputDtoMock)).thenReturn(engineMock);
        when(engineRepo.save(engineMock)).thenReturn(engineMock);

        EngineDto resultTest = engineService.saveEngine(engineInputDtoMock);
        assertEquals(resultTest, engineDtoMock);
    }

    @Test
    public void updateCatTest() {
        when(engineMapper.getModelFromDto(engineDtoMock)).thenReturn(engineMock);
        when(engineRepo.save(engineMock)).thenReturn(engineMock);

        EngineDto resultTest = engineService.updateEngine(engineDtoMock);
        assertEquals(resultTest, engineDtoMock);
    }

    @Test
    public void deleteEngineTest() {
        int testId = 66;
        doNothing().when(engineRepo).deleteById(testId);
        engineService.deleteEngine(testId);
    }

    @Test
    public void deleteEngineNotExistCaseTest() {
        int testId = 66;
        doThrow(new EmptyResultDataAccessException(1)).when(engineRepo).deleteById(testId);
        assertThrows(IllegalArgumentException.class, () -> engineService.deleteEngine(testId));
    }
}
