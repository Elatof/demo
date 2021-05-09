package com.korbiak.demo.mapper.impl;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.mapper.EngineMapper;
import com.korbiak.demo.model.Engine;
import org.springframework.stereotype.Component;

@Component
public class EngineMapperImpl implements EngineMapper {
    @Override
    public EngineDto getDtoFromModel(Engine engine) {
        return EngineDto.builder()
                .id(engine.getId())
                .engineModel(engine.getEngineModel())
                .horsePower(engine.getHorsePower())
                .numberOfCylinders(engine.getNumberOfCylinders())
                .volume(engine.getVolume())
                .build();
    }

    @Override
    public Engine getModelFromDto(EngineInputDto engineInputDto) {
        return Engine.builder()
                .engineModel(engineInputDto.getEngineModel())
                .horsePower(engineInputDto.getHorsePower())
                .numberOfCylinders(engineInputDto.getNumberOfCylinders())
                .volume(engineInputDto.getVolume())
                .build();
    }

    @Override
    public Engine getModelFromDto(EngineDto engineDto) {
        return Engine.builder()
                .id(engineDto.getId())
                .engineModel(engineDto.getEngineModel())
                .horsePower(engineDto.getHorsePower())
                .numberOfCylinders(engineDto.getNumberOfCylinders())
                .volume(engineDto.getVolume())
                .build();
    }
}
