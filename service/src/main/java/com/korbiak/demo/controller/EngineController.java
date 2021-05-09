package com.korbiak.demo.controller;

import com.korbiak.demo.dto.input.EngineInputDto;
import com.korbiak.demo.dto.output.EngineDto;
import com.korbiak.demo.service.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo-api/engines/")
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;

    @GetMapping
    public List<EngineDto> getAllEngines() {
        return engineService.getAllEngines();
    }

    @GetMapping(path = "{engineId}")
    public EngineDto getEngineById(@PathVariable int engineId) {
        return engineService.getEngineById(engineId);
    }

    @PostMapping
    public EngineDto saveNewEngine(@RequestBody @Validated EngineInputDto engineInputDto) {
        return engineService.saveEngine(engineInputDto);
    }

    @PutMapping
    public EngineDto updateEngine(@RequestBody @Validated EngineDto engineDto) {
        return engineService.updateEngine(engineDto);
    }

    @DeleteMapping("{engineId}")
    public void deleteEngine(@PathVariable int engineId) {
        engineService.deleteEngine(engineId);
    }
}
