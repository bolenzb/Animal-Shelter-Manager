package com.techelevator.model.dto;

public class EmailResponseDto {

    private boolean hasEmail;

    public EmailResponseDto() {

    }
    public EmailResponseDto(boolean result) {
        this.hasEmail = result;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(boolean hasEmail) {
        this.hasEmail = hasEmail;
    }
}
