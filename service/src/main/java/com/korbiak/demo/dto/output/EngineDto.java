package com.korbiak.demo.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EngineDto {

    private int id;
    @NotBlank
    private String engineModel;
    private int horsePower;
    private int numberOfCylinders;
    private double volume;
}
