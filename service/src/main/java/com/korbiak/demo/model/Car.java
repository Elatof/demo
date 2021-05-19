package com.korbiak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "car")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "price")
    private int price;

    @Column(name = "date_of_manufacture")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "car_company_id")
    private CarCompany company;

    @ManyToMany
    @JoinTable(
            name = "car_engine",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "engine_id") }
    )
    private List<Engine> engines;
}
