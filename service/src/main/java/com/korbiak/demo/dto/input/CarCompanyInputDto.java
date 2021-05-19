package com.korbiak.demo.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCompanyInputDto {
    @Size(max = 25)
    private String name;

    @Size(max = 25)
    private String country;

    @Size(max = 25)
    private String city;
}
