package com.sprints.OnlineVotingSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vote_id;
    @Column(name = "vote_time",nullable = false)
    private LocalDateTime voteTime;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @OneToOne
    @JoinColumn(name = "voter_id")
    private User voter;

    public Vote() {
    }

    public void setVote_id(Long voteId)
    {
        this.vote_id = voteId;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Long getVote_id() {
        return vote_id;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public Election getElection() {
        return election;
    }

    public User getVoter() {
        return voter;
    }
}
