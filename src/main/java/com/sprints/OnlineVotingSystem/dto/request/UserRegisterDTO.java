package com.sprints.OnlineVotingSystem.dto.request;

import com.sprints.OnlineVotingSystem.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//to register users
public class UserRegisterDTO {

    private String username;
    @NotBlank @Email
    private String email;
    @Size(min = 8)
    private String password;
    private String city;
    @NotNull
    private Role role;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String username, String email, String password, String city, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public Role getRole() {
        return role;
    }
}
