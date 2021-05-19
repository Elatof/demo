package com.korbiak.demo.controller;

import com.korbiak.demo.dto.input.CarCompanyInputDto;
import com.korbiak.demo.dto.output.CarCompanyDto;
import com.korbiak.demo.service.CarCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo-api/companies/")
@RequiredArgsConstructor
public class CarCompanyController {

    private final CarCompanyService companyService;

    @GetMapping
    public List<CarCompanyDto> getAllCarCompanies() {
        return companyService.getAllCarCompanies();
    }

    @GetMapping(path = "{companyId}")
    public CarCompanyDto getCarCompanyById(@PathVariable int companyId) {
        return companyService.getCarCompanyById(companyId);
    }

    @PostMapping
    public CarCompanyDto saveNewCarCompany(@RequestBody @Validated CarCompanyInputDto companyInputDto) {
        return companyService.saveCarCompany(companyInputDto);
    }

    @PutMapping
    public CarCompanyDto updateCarCompany(@RequestBody @Validated CarCompanyDto inputDto) {
        return companyService.updateCarCompany(inputDto);
    }

    @PostMapping("reset")
    public CarCompanyDto resetCarCompany(@RequestBody @Validated CarCompanyInputDto companyInputDto) {
        return companyService.resetCarCompany(companyInputDto);
    }

    @DeleteMapping("{companyId}")
    public void deleteCarCompany(@PathVariable int companyId) {
        companyService.deleteCarCompany(companyId);
    }
}
