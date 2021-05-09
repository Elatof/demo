package com.korbiak.demo.service;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.EngineDto;

import java.util.List;

public interface EngineService {
    List<EngineDto> getAllEngines();

    EngineDto getEngineById(int id);

    EngineDto saveEngine(EngineInputDto engineInputDto);

    EngineDto updateEngine(EngineDto engineDto);

    void deleteEngine(int id);
}
