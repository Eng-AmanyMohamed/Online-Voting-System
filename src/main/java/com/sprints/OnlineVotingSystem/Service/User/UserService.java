package com.sprints.OnlineVotingSystem.Service.User;

import com.sprints.OnlineVotingSystem.dto.request.UserRegisterDTO;
import com.sprints.OnlineVotingSystem.dto.response.UserResponseDTO;
import com.sprints.OnlineVotingSystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long id);
}
