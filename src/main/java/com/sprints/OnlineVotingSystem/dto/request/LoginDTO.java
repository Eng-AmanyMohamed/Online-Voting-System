package com.sprints.OnlineVotingSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//to login users
public class LoginDTO {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String username;

    public LoginDTO() {
    }

    public LoginDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
