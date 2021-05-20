package com.korbiak.demo.repository;

import com.korbiak.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    List<Car> findAllByCompanyIdIn(Iterable<Integer> companyIds);

    List<Car> findAllByEnginesIdIn(Iterable<Integer> engineIds);

    Car findCarByModelName(String modelName);
}
