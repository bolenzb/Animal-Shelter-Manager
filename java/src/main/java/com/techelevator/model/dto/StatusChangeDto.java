package com.techelevator.model.dto;

public class StatusChangeDto {

    private int statusId;
    private int pendingId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPendingId() {
        return pendingId;
    }

    public void setPendingId(int pendingId) {
        this.pendingId = pendingId;
    }
}
