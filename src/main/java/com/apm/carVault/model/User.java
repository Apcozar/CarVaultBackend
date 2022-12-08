package com.apm.carVault.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
public class User {

    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Car> cars;

    @Column(name="user_firstname", nullable = false)
    private String firstname;

    @Column(name="user_surname", nullable = false)
    private String surname;

    @Column(name="user_username", nullable = false)
    private String username;

    @Column(name="user_email", nullable = false)
    private String email;

    @Column(name="user_phone", nullable = false)
    private String phone;

    @Lob
    @Column(name="user_picture", nullable = true , columnDefinition = "LONGTEXT")
    private String profilePicture;

    public User() {
    }

    public User(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }


    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(cars, user.cars) && Objects.equals(firstname, user.firstname) && Objects.equals(surname, user.surname) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(profilePicture, user.profilePicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cars, firstname, surname, username, email, phone, profilePicture);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cars=" + cars +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
