package com.sprints.OnlineVotingSystem.Service.Auth;


import com.sprints.OnlineVotingSystem.dto.request.AuthRequest;
import com.sprints.OnlineVotingSystem.dto.response.AuthResponse;
import com.sprints.OnlineVotingSystem.dto.request.UserRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
