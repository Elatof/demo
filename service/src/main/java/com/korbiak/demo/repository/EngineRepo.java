package com.korbiak.demo.repository;

import com.korbiak.demo.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepo extends JpaRepository<Engine, Integer> {
}
