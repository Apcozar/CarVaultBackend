package com.apm.carVault.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarTransaction {
    @Id
    @Column(name="car_transaction_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="transaction_hash", nullable = true)
    private String hash;

    @Column(name="transaction_date", nullable = true)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "car_id",
            nullable = false, updatable = false)
    private Car car;

    public CarTransaction() {
    }

    public CarTransaction(Long id, String hash, Car car, Date date) {
        this.id = id;
        this.hash = hash;
        this.car = car;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
