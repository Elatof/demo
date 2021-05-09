package com.korbiak.demo.service.impl;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.mapper.EngineMapper;
import com.korbiak.demo.model.Company;
import com.korbiak.demo.model.Engine;
import com.korbiak.demo.repository.EngineRepo;
import com.korbiak.demo.service.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EngineServiceImpl implements EngineService {

    private final EngineRepo engineRepo;
    private final EngineMapper engineMapper;

    @Override
    public List<EngineDto> getAllEngines() {
        List<Engine> engines = engineRepo.findAll();
        return engines.stream()
                .map(engineMapper::getDtoFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public EngineDto getEngineById(int id) {
        Engine engine = engineRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Engine not found with id = " + id));

        return engineMapper.getDtoFromModel(engine);
    }

    @Override
    public EngineDto saveEngine(EngineInputDto engineInputDto) {
        Engine engine = engineMapper.getModelFromDto(engineInputDto);
        engineRepo.save(engine);

        return engineMapper.getDtoFromModel(engine);
    }

    @Override
    public EngineDto updateEngine(EngineDto engineDto) {
        Engine engine = engineMapper.getModelFromDto(engineDto);
        engineRepo.save(engine);

        return engineMapper.getDtoFromModel(engine);
    }

    @Override
    public void deleteEngine(int id) {
        if (engineRepo.existsById(id)){
            engineRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Engine not exist with id = " + id);
        }
    }
}
