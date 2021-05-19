package com.korbiak.demo.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineDto {

    private int id;

    @Size(max = 25)
    private String engineModel;

    @Min(50)
    private int horsePower;

    @Min(1)
    private int numberOfCylinders;

    @Min(1)
    private double volume;
}
