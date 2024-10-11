package com.techelevator.model.dto;

public class AdoptionAppDto {

    private int petId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int applicantStatus;
    private boolean responseOne;
    private boolean responseTwo;
    private boolean responseThree;
    private boolean responseFour;


    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(int applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public boolean isResponseOne() {
        return responseOne;
    }

    public void setResponseOne(boolean responseOne) {
        this.responseOne = responseOne;
    }

    public boolean isResponseTwo() {
        return responseTwo;
    }

    public void setResponseTwo(boolean responseTwo) {
        this.responseTwo = responseTwo;
    }

    public boolean isResponseThree() {
        return responseThree;
    }

    public void setResponseThree(boolean responseThree) {
        this.responseThree = responseThree;
    }

    public boolean isResponseFour() {
        return responseFour;
    }

    public void setResponseFour(boolean responseFour) {
        this.responseFour = responseFour;
    }
}

