package com.sprints.OnlineVotingSystem.repository;

import com.sprints.OnlineVotingSystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    List<Candidate> findAllByElectionId(Long electionId);

    Optional<Candidate> findByCandidateName(String candidateName);

    boolean existsByName(String name);
}
