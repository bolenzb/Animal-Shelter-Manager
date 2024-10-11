package com.techelevator.model;

import java.util.Date;

public class Pet {
    private int petId;
    private String name;
    private String gender;
    private float weight;
    private String species;
    private String breed;
    private String color;
    private Date birthday;
    private String description;
    private int status;

    public Pet() {

    }

    public Pet(int petId, String name, String gender, float weight, String species, String breed, String color, Date birthday, int status) {
        this.petId = petId;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.birthday = birthday;
        this.description = description;
        this.status = status;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
