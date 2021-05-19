package com.korbiak.demo.dto.input;

import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.dto.output.EngineDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInputDto {
    @Size(max = 25)
    private String modelName;

    @Min(1)
    private int price;

    @NotNull
    private Date date;

    @NotNull
    private CarCompanyDto company;

    @NotNull
    private List<EngineDto> engines;
}
