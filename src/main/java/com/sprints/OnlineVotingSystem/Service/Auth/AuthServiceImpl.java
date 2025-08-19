package com.sprints.OnlineVotingSystem.Service.Auth;

import com.sprints.OnlineVotingSystem.dto.request.AuthRequest;
import com.sprints.OnlineVotingSystem.dto.response.AuthResponse;
import com.sprints.OnlineVotingSystem.dto.request.UserRegisterDTO;
import com.sprints.OnlineVotingSystem.model.Role;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.repository.UserRepository;

import com.sprints.OnlineVotingSystem.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        String token = jwtService.generateToken(username, role);

        return new AuthResponse(token);
    }
}
