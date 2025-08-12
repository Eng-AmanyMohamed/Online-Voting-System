package com.sprints.OnlineVotingSystem.dto.response;

import java.time.LocalDateTime;

public class VoteResponseDTO {
    private Long voteId;
    private Long voterId;
    private Long candidateId;
    private Long electionId;
    private LocalDateTime voteTime;

    public VoteResponseDTO() {
    }

    public VoteResponseDTO(Long voteId, Long voterId, Long candidateId, Long electionId, LocalDateTime voteTime) {
        this.voteId = voteId;
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.electionId = electionId;
        this.voteTime = voteTime;
    }


    public Long getVoteId() {
        return voteId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }
}
