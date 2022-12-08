package com.apm.carVault.repository;

import com.apm.carVault.model.Car;
import com.apm.carVault.model.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}
