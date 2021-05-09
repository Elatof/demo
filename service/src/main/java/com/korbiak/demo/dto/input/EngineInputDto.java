package com.korbiak.demo.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EngineInputDto {

    @NotBlank
    private String engineModel;
    private int horsePower;
    private int numberOfCylinders;
    private double volume;
}
