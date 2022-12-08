package com.apm.carVault.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CarImage {

    @Id
    @Column(name="car_image_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="image_content", nullable = true , columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "car_id",
            nullable = false, updatable = true)
    private Car car;

    public CarImage() {
    }

    public CarImage(String content, Car car) {
        this.content = content;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CarImage{" +
                "content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarImage that = (CarImage) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
