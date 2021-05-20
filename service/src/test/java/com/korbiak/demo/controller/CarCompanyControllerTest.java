package com.korbiak.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.service.CarCompanyService;
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
public class CarCompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarCompanyService companyServiceMock;

    @Autowired
    private ObjectMapper objMapper;

    private CarCompanyDto getMockCarCompany() {
        return CarCompanyDto.builder()
                .id(1)
                .city("City")
                .name("Name")
                .country("Country")
                .build();
    }

    private CarCompanyInputDto getMockInputCarCompany() {
        return CarCompanyInputDto.builder()
                .city("City")
                .name("Name")
                .country("Country")
                .build();
    }


    @Test
    public void getAllCarCompaniesTest() throws Exception {
        CarCompanyDto companyDto = getMockCarCompany();
        when(companyServiceMock.getAllCarCompanies())
                .thenReturn(List.of(companyDto));

        mockMvc.perform(get("/demo-api/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(companyDto.getId()))
                .andExpect(jsonPath("$.[0].city").value(companyDto.getCity()))
                .andExpect(jsonPath("$.[0].name").value(companyDto.getName()))
                .andExpect(jsonPath("$.[0].country").value(companyDto.getCountry()));
    }

    @Test
    public void getCarCompanyByIdTest() throws Exception {
        CarCompanyDto companyDto = getMockCarCompany();
        when(companyServiceMock.getCarCompanyById(companyDto.getId()))
                .thenReturn(companyDto);

        mockMvc.perform(get("/demo-api/companies/{id}", companyDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(companyDto.getId()))
                .andExpect(jsonPath("$.city").value(companyDto.getCity()))
                .andExpect(jsonPath("$.name").value(companyDto.getName()))
                .andExpect(jsonPath("$.country").value(companyDto.getCountry()));
    }

    @Test
    public void updateCarCompanyTest() throws Exception {
        CarCompanyDto companyDto = getMockCarCompany();
        when(companyServiceMock.updateCarCompany(companyDto))
                .thenReturn(companyDto);

        mockMvc.perform(put("/demo-api/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(companyDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(companyDto.getId()))
                .andExpect(jsonPath("$.city").value(companyDto.getCity()))
                .andExpect(jsonPath("$.name").value(companyDto.getName()))
                .andExpect(jsonPath("$.country").value(companyDto.getCountry()));
    }

    @Test
    public void resetCarCompanyTest() throws Exception {
        CarCompanyDto companyDto = getMockCarCompany();
        CarCompanyInputDto companyInputDto = getMockInputCarCompany();
        when(companyServiceMock.resetCarCompany(companyInputDto))
                .thenReturn(companyDto);

        mockMvc.perform(post("/demo-api/companies/reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(companyDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(companyDto.getId()))
                .andExpect(jsonPath("$.city").value(companyDto.getCity()))
                .andExpect(jsonPath("$.name").value(companyDto.getName()))
                .andExpect(jsonPath("$.country").value(companyDto.getCountry()));
    }

    @Test
    public void saveCarCompanyTest() throws Exception {
        CarCompanyInputDto companyInputDto = getMockInputCarCompany();
        CarCompanyDto carCompanyDto = getMockCarCompany();
        when(companyServiceMock.saveCarCompany(companyInputDto))
                .thenReturn(carCompanyDto);

        mockMvc.perform(post("/demo-api/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsBytes(companyInputDto))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carCompanyDto.getId()))
                .andExpect(jsonPath("$.city").value(carCompanyDto.getCity()))
                .andExpect(jsonPath("$.name").value(carCompanyDto.getName()))
                .andExpect(jsonPath("$.country").value(carCompanyDto.getCountry()));
    }

    @Test
    public void deleteCarCompanyTest() throws Exception {
        int testId = 666;
        doNothing().when(companyServiceMock).deleteCarCompany(testId);

        mockMvc.perform(delete("/demo-api/companies/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
