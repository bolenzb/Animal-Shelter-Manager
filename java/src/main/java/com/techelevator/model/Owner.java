package com.techelevator.model;

import java.util.Date;

public class Owner {
    private int ownerId;
    private String ownerName;
    private int petId;
    private Date adoptionDate;

    public Owner() {

    }

    public Owner(int ownerId, String ownerName, int petId, Date adoptionDate) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.petId = petId;
        this.adoptionDate = adoptionDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
}
