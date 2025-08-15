package com.sprints.OnlineVotingSystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long candidate_id;
    private String name;
    private String city;
    private String party;


    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Vote> votes =new ArrayList<>();
    public Candidate() {
    }

    public void setCandidate_id(Long candidate_id) {
        this.candidate_id = candidate_id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Long getCandidate_id() {
        return candidate_id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public Election getElection() {
        return election;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public String getCity() {
        return city;
    }
}
