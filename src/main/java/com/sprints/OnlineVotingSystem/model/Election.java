package com.sprints.OnlineVotingSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="elections")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long election_id;
    private String title;
    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;
    @Column(name = "city",nullable = false)
    private String city;


    @OneToMany(mappedBy = "election")
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "election")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "election")
    private List<Vote> votes = new ArrayList<>();


    public Election() {
    }

    public void setElection_id(Long election_id) {
        this.election_id = election_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Long getElection_id() {
        return election_id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
