package com.korbiak.demo.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInputDto {
    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    private String city;
}
