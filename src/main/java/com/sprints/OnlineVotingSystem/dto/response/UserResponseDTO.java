package com.sprints.OnlineVotingSystem.dto.response;

import com.sprints.OnlineVotingSystem.model.Role;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String city;
    private Role role;
    private Long electionId;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String name, String email, String city, Role role, Long electionId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.role = role;
        this.electionId = electionId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public Role getRole() {
        return role;
    }

    public Long getElectionId() {
        return electionId;
    }
}
