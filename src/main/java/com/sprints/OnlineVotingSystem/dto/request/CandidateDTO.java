package com.sprints.OnlineVotingSystem.dto.request;

//Create/update/delete Candidate
public class CandidateDTO {
    private String name;
    private String party;
    private Long electionId;

    public CandidateDTO() {
    }

    public CandidateDTO(String name, String party, Long electionId) {
        this.name = name;
        this.party = party;
        this.electionId = electionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public Long getElectionId() {
        return electionId;
    }
}
