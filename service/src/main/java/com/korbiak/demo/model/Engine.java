package com.korbiak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "engine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "engine_model")
    private String engineModel;

    @Column(name = "horse_power")
    private int horsePower;

    @Column(name = "number_of_cylinders")
    private int numberOfCylinders;

    @Column(name = "volume")
    private double volume;

    @ManyToMany
    @JoinTable(
            name = "car_engine",
            joinColumns = { @JoinColumn(name = "engine_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") }
    )
    private List<Car> cars;
}
