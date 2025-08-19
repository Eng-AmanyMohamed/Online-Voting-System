package com.sprints.OnlineVotingSystem.Service.Vote;

import com.sprints.OnlineVotingSystem.exception.customException.DataNotFoundException;
import com.sprints.OnlineVotingSystem.exception.customException.DuplicateException;
import com.sprints.OnlineVotingSystem.exception.customException.ElectionNotActiveException;
import com.sprints.OnlineVotingSystem.exception.customException.UneligibleVoterException;
import com.sprints.OnlineVotingSystem.model.Candidate;
import com.sprints.OnlineVotingSystem.model.Election;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.model.Vote;
import com.sprints.OnlineVotingSystem.repository.CandidateRepository;
import com.sprints.OnlineVotingSystem.repository.ElectionRepository;
import com.sprints.OnlineVotingSystem.repository.UserRepository;
import com.sprints.OnlineVotingSystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class VoteServiceImpl {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository
                     , ElectionRepository electionRepository, CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
    }

    public Optional<Vote> vote(Long userId, Long candidateId, Long electionId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new DataNotFoundException("Election not found"));
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException("Candidate not found"));

        checkVoteTime(user, election);
       // checkElectionCity(user, election);
        checkDoubleVoting(user, election);

        LocalDateTime now = LocalDateTime.now();

        Vote vote = new Vote();
        vote.setVoter(user);
        vote.setCandidate(candidate);
        vote.setElection(election);
        vote.setVoteTime(now);

        return Optional.of(voteRepository.save(vote));
    }

    private void checkVoteTime(User user, Election election){
        if (voteRepository.findByVoterAndElection(user, election).isPresent()) {
            throw new DuplicateException("You have already voted.");
        }
    }

    private void checkDoubleVoting(User user, Election election){
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(election.getStartTime()) || now.isAfter(election.getEndTime())) {
            throw new ElectionNotActiveException("Voting is currently closed.");
        }
    }

//    private void checkElectionCity(User user, Election election){
//        if(!Objects.equals(user.getCity(), election.getCity())){
//            throw new UneligibleVoterException("Ineligible Voter");
//        }
//    }

}
