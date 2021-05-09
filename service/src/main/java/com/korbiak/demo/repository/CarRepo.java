package com.korbiak.demo.repository;

import com.korbiak.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
}
