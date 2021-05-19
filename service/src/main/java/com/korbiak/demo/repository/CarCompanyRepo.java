package com.korbiak.demo.repository;

import com.korbiak.demo.model.CarCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCompanyRepo extends JpaRepository<CarCompany, Integer> {
    CarCompany getCompanyByName(String name);
}
