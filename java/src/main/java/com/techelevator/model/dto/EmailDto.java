package com.techelevator.model.dto;

import javax.validation.constraints.NotEmpty;

public class EmailDto {
    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
