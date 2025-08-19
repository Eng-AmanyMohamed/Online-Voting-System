package com.sprints.OnlineVotingSystem.Service.Candidate;

import com.sprints.OnlineVotingSystem.Service.Election.ElectionService;
import com.sprints.OnlineVotingSystem.dto.request.CandidateDTO;
import com.sprints.OnlineVotingSystem.exception.customException.DataNotFoundException;
import com.sprints.OnlineVotingSystem.exception.customException.DuplicateException;
import com.sprints.OnlineVotingSystem.exception.customException.ElectionNotActiveException;
import com.sprints.OnlineVotingSystem.model.Candidate;
import com.sprints.OnlineVotingSystem.model.Election;
import com.sprints.OnlineVotingSystem.repository.CandidateRepository;
import com.sprints.OnlineVotingSystem.repository.ElectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    private CandidateRepository candidateRepository;
    private ElectionRepository electionRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, ElectionRepository electionRepository) {
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public CandidateDTO addCandidate(CandidateDTO candidateDTO) {
        if (candidateRepository.existsByName(candidateDTO.getName())) {
            throw new DuplicateException("This Candidate Already Exists");
        }
       Election election=electionRepository.findById(candidateDTO.getElectionId())
               .orElseThrow(()->new DataNotFoundException("Election Not Found"));

        if(election.getEndTime().isBefore(LocalDateTime.now()) ){
            throw new ElectionNotActiveException("Election is not Active");
        }
        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setParty(candidateDTO.getParty());
        candidate.setCity(candidateDTO.getCity());
        candidate.setElection(election);

        candidateRepository.save(candidate);

        return candidateDTO;
    }


    @Override
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate= candidateRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Candidate Not Found"));
        CandidateDTO candidateDTO=new CandidateDTO();
        candidateDTO.setName(candidate.getName());
        candidateDTO.setParty(candidate.getParty());
        candidateDTO.setCity(candidate.getCity());
        candidateDTO.setElectionId(candidate.getElection().getElection_id());
        return candidateDTO;
    }

    @Override
    public List<CandidateDTO> getAllCandidatesPerElection(Long electionId) {
        List<Candidate> candidateList=candidateRepository.findAllByElectionId(electionId);
        List<CandidateDTO> candidateDTOList=new ArrayList<>();
        for(Candidate candidate:candidateList){
            CandidateDTO candidateDTO=new CandidateDTO();
            candidateDTO.setName(candidate.getName());
            candidateDTO.setParty(candidate.getParty());
            candidateDTO.setCity(candidate.getCity());
            candidateDTO.setElectionId(candidate.getElection().getElection_id());
            candidateDTOList.add(candidateDTO);
        }
        return candidateDTOList;
    }

    @Override
    public CandidateDTO updateCandidate(CandidateDTO updatedcandidateDTO, Long id) {
        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Candidate Not Found"));
        Election election=electionRepository.findById(updatedcandidateDTO.getElectionId())
                .orElseThrow(()->new DataNotFoundException("Election Not Found"));
        candidate.setName(updatedcandidateDTO.getName());
        candidate.setParty(updatedcandidateDTO.getParty());
        candidate.setCity(updatedcandidateDTO.getCity());
        candidate.setElection(election);
        candidateRepository.save(candidate);
        return  updatedcandidateDTO;
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Candidate Not Found"));
        candidateRepository.delete(candidate);
    }

}
