package com.korbiak.demo.dto.output;

import com.korbiak.demo.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CarDto {
    private int id;

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
