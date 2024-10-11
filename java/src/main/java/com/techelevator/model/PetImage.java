package com.techelevator.model;

public class PetImage {
    private int imageId;
    private int petId;
    private String name;
    private String type;
    private byte[] data;

    public PetImage() {

    }
    public PetImage(int imageId, int petId, String name, String type, byte[] data) {
        this.imageId = imageId;
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
