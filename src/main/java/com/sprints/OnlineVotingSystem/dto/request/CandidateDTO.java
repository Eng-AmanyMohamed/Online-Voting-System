package com.sprints.OnlineVotingSystem.dto.request;

//Create/update/delete Candidate
public class CandidateDTO {
    private String name;
    private String party;
    private Long electionId;
    private String city;
    public CandidateDTO() {
    }

    public CandidateDTO(String name, String party, Long electionId,String city) {
        this.name = name;
        this.party = party;
        this.electionId = electionId;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getCity() {
        return city;
    }
}
