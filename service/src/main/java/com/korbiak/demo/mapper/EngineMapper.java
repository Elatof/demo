package com.korbiak.demo.mapper;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.model.Engine;
import org.mapstruct.Mapper;

public interface EngineMapper {

    EngineDto getDtoFromModel(Engine engine);

    Engine getModelFromDto(EngineInputDto engineInputDto);

    Engine getModelFromDto(EngineDto engineDto);
}
