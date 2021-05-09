package com.korbiak.demo.repository;

import com.korbiak.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Company getCompanyByName(String name);
}
