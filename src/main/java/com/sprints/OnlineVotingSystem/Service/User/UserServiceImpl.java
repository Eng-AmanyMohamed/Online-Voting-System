package com.sprints.OnlineVotingSystem.Service.User;

import com.sprints.OnlineVotingSystem.dto.request.UserRegisterDTO;
import com.sprints.OnlineVotingSystem.dto.response.UserResponseDTO;
import com.sprints.OnlineVotingSystem.exception.customException.DuplicateException;
import com.sprints.OnlineVotingSystem.exception.customException.DataNotFoundException;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO registerUser(UserRegisterDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("User with this email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔒 hash
        user.setCity(dto.getCity());
        user.setRole(dto.getRole());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getUser_id(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCity(),
                savedUser.getRole(),
                savedUser.getElection() != null ? savedUser.getElection().getElection_id() : null
        );
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(
                        u.getUser_id(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getCity(),
                        u.getRole(),
                        u.getElection() != null ? u.getElection().getElection_id() : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new DataNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
