package com.sprints.OnlineVotingSystem.dto.response;

//to show the result of election
public class ResultDTO {
    private String candidateName;
    private String party;
    private long voteCount;

    public ResultDTO() {
    }

    public ResultDTO(String candidateName, String party, long voteCount) {
        this.candidateName = candidateName;
        this.party = party;
        this.voteCount = voteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getParty() {
        return party;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
