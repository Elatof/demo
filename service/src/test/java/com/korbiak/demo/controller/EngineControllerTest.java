package com.korbiak.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.service.EngineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EngineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EngineService engineServiceMock;

    @Autowired
    private ObjectMapper objMapper;

    private EngineDto getMockEngine() {
        return EngineDto.builder()
                .id(1)
                .horsePower(150)
                .numberOfCylinders(4)
                .volume(2)
                .engineModel("EngineModel")
                .build();
    }

    private EngineInputDto getMockInputEngine() {
        return EngineInputDto.builder()
                .horsePower(150)
                .numberOfCylinders(4)
                .volume(2)
                .engineModel("EngineModel")
                .build();
    }


    @Test
    public void getAllEnginesTest() throws Exception {
        EngineDto engineDto = getMockEngine();
        when(engineServiceMock.getAllEngines())
                .thenReturn(List.of(engineDto));

        mockMvc.perform(get("/demo-api/engines/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(engineDto.getId()))
                .andExpect(jsonPath("$.[0].engineModel").value(engineDto.getEngineModel()))
                .andExpect(jsonPath("$.[0].volume").value(engineDto.getVolume()))
                .andExpect(jsonPath("$.[0].numberOfCylinders").value(engineDto.getNumberOfCylinders()))
                .andExpect(jsonPath("$.[0].horsePower").value(engineDto.getHorsePower()));
    }

    @Test
    public void getEngineByIdTest() throws Exception {
        EngineDto engineDto = getMockEngine();
        when(engineServiceMock.getEngineById(engineDto.getId()))
                .thenReturn(engineDto);

        mockMvc.perform(get("/demo-api/engines/{id}", engineDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(engineDto.getId()))
                .andExpect(jsonPath("$.engineModel").value(engineDto.getEngineModel()))
                .andExpect(jsonPath("$.volume").value(engineDto.getVolume()))
                .andExpect(jsonPath("$.numberOfCylinders").value(engineDto.getNumberOfCylinders()))
                .andExpect(jsonPath("$.horsePower").value(engineDto.getHorsePower()));
    }

    @Test
    public void updateEngineTest() throws Exception {
        EngineDto engineDto = getMockEngine();
        when(engineServiceMock.updateEngine(engineDto))
                .thenReturn(engineDto);

        mockMvc.perform(put("/demo-api/engines/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(engineDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(engineDto.getId()))
                .andExpect(jsonPath("$.engineModel").value(engineDto.getEngineModel()))
                .andExpect(jsonPath("$.volume").value(engineDto.getVolume()))
                .andExpect(jsonPath("$.numberOfCylinders").value(engineDto.getNumberOfCylinders()))
                .andExpect(jsonPath("$.horsePower").value(engineDto.getHorsePower()));
    }

    @Test
    public void saveEngineTest() throws Exception {
        EngineDto engineDto = getMockEngine();
        EngineInputDto engineInputDto = getMockInputEngine();
        when(engineServiceMock.saveEngine(engineInputDto))
                .thenReturn(engineDto);

        mockMvc.perform(post("/demo-api/engines/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(engineInputDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(engineDto.getId()))
                .andExpect(jsonPath("$.engineModel").value(engineDto.getEngineModel()))
                .andExpect(jsonPath("$.volume").value(engineDto.getVolume()))
                .andExpect(jsonPath("$.numberOfCylinders").value(engineDto.getNumberOfCylinders()))
                .andExpect(jsonPath("$.horsePower").value(engineDto.getHorsePower()));
    }

    @Test
    public void deleteCarCompanyTest() throws Exception {
        int testId = 666;
        doNothing().when(engineServiceMock).deleteEngine(testId);

        mockMvc.perform(delete("/demo-api/engines/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
