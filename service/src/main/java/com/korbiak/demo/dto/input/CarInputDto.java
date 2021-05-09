package com.korbiak.demo.dto.input;

import com.korbiak.demo.dto.output.CompanyDto;
import com.korbiak.demo.dto.output.EngineDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInputDto {
    @NotBlank
    private String modelName;

    private int price;
    @NotNull
    private Date date;

    @NotNull
    private CompanyDto company;

    @NotNull
    private List<EngineDto> engines;
}
