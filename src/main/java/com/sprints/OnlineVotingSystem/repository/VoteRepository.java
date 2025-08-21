package com.sprints.OnlineVotingSystem.repository;

import com.sprints.OnlineVotingSystem.model.Election;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote,Long> {
    Optional<Vote> findByVoterAndElection(User voter, Election election); // to check if voter has already vote or not
}
