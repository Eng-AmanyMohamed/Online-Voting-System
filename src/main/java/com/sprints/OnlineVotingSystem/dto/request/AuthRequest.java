package com.sprints.OnlineVotingSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//to login users
@Data
public class AuthRequest {
    @NotBlank @Email
    private String email;
    @Size(min = 8)
    private String password;
}
