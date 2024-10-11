package com.techelevator.model.dto;

public class AdoptAppStatusChangeDto {

    private int statusId;
    private int ApplicantId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getApplicantId() {
        return ApplicantId;
    }

    public void setApplicantId(int applicantId) {
        ApplicantId = applicantId;
    }
}
