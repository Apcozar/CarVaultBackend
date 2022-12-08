package com.apm.carVault.repository;

import com.apm.carVault.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByVin(String vin);
}
