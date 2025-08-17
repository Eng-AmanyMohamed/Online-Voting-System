package com.sprints.OnlineVotingSystem.Service.Candidate;

import com.sprints.OnlineVotingSystem.dto.request.CandidateDTO;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    CandidateDTO addCandidate(CandidateDTO candidateDTO);
    CandidateDTO getCandidateById(Long id);
    List<CandidateDTO> getAllCandidatesPerElection(Long electionId);
    CandidateDTO updateCandidate(CandidateDTO updatedcandidateDTO, Long id);
    void deleteCandidate(Long id);

}
