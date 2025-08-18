package com.sprints.OnlineVotingSystem.repository;

import com.sprints.OnlineVotingSystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    @Query("SELECT c FROM Candidate c WHERE c.election.election_id= :electionId")
    List<Candidate> findAllByElectionId(@Param("electionId") Long electionId);

    boolean existsByName(String name);
}
