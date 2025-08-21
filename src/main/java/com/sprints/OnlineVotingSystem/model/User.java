package com.sprints.OnlineVotingSystem.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // Voter, Admin

    @OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
    private Vote vote;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    public User() {
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Long getUser_id() {
        return user_id;
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

    public Vote getVote() {
        return vote;
    }

    public Election getElection() {
        return election;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
