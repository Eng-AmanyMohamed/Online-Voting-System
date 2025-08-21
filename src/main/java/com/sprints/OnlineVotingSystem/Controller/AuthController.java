package com.sprints.OnlineVotingSystem.Controller;

import com.sprints.OnlineVotingSystem.Service.Auth.AuthService;
import com.sprints.OnlineVotingSystem.dto.request.AuthRequest;
import com.sprints.OnlineVotingSystem.dto.response.AuthResponse;
import com.sprints.OnlineVotingSystem.dto.request.UserRegisterDTO;
import com.sprints.OnlineVotingSystem.Service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
