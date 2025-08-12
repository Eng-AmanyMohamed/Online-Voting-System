package com.sprints.OnlineVotingSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// to submit Vote

public class VoteDTO {
    @NotNull(message = "Candidate ID is required")
    @Positive
    private Long candidateId;

    public VoteDTO() {
    }

    public VoteDTO(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
