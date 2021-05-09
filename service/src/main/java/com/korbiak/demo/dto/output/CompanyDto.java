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
public class CompanyDto {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    private String city;
}
