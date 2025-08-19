package com.sprints.OnlineVotingSystem.Service.Vote;

import com.sprints.OnlineVotingSystem.model.Election;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.model.Vote;

import java.util.Optional;


public interface VoteService {
    Optional<Vote> vote(Long userId, Long candidateId, Long electionId);

    void checkVoteTime(User user, Election election);

    void checkDoubleVoting(User user, Election election);

    //void checkElectionCity(User user, Election election);

}
