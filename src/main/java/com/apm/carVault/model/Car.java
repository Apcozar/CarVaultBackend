package com.apm.carVault.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @Column(name="car_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false)
    private User owner;

    @Column(name="car_brand", nullable = false)
    private String brand;

    @Column(name="car_horsepower", nullable = false)
    private int horsepower;

    @Column(name="car_model", nullable = false)
    private String model;

    @Column(name="car_vin", nullable = true)
    private String vin;

    @Column(name="car_description", nullable = true)
    private String description;

    @Column(name="car_km", nullable = true)
    private int kilometers;

    @Column(name="car_year", nullable = true)
    private int year;

    @Column(name="car_address", nullable = true)
    private String address;

    @Column(name="car_manufacturer", nullable = true)
    private String manufacturer;

    @Column(name="car_origin", nullable = true)
    private String origin;

    @Column(name="car_fuel", nullable = true)
    private String fuel;

    @Column(name="car_color", nullable = true)
    private String color;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarDocument> documents;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarTransaction> transactions;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarImage> images;

    public Car() {
        this.documents = new ArrayList<>();
        this.images = new ArrayList<>();
        this.transactions = new ArrayList<>();

    }

    public Car(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.documents = new ArrayList<>();
        this.images = new ArrayList<>();
        this.transactions = new ArrayList<>();

    }

    public Car(Long id, String brand, String model, String vin, String description, int kilometers, int year, String address, String manufacturer, String origin, String fuel, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.description = description;
        this.kilometers = kilometers;
        this.year = year;
        this.address = address;
        this.manufacturer = manufacturer;
        this.origin = origin;
        this.fuel = fuel;
        this.color = color;
        this.documents = new ArrayList<>();
        this.images = new ArrayList<>();
        this.transactions = new ArrayList<>();

    }

    public List<CarTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CarTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<CarDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<CarDocument> documents) {
        this.documents = documents;
    }

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public User getOwner() {
        return owner;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
