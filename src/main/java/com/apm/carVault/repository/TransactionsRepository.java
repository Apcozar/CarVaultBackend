package com.apm.carVault.repository;


import com.apm.carVault.model.Car;
import com.apm.carVault.model.CarTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<CarTransaction, Long> {
}
