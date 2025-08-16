package com.sprints.OnlineVotingSystem.Service.Election;

import com.sprints.OnlineVotingSystem.dto.request.ElectionDTO;
import com.sprints.OnlineVotingSystem.model.Election;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ElectionService {

    Election createElection(ElectionDTO electionDTO);

    Election updateElection(Long electionId, ElectionDTO electionDTO);

    void deleteElection(Long electionId);

    Optional<Election> getElectionById(Long electionId);

    List<Election> getAllElections();

    List<Election> getActiveElections();

    List<Election> getUpcomingElections();

    List<Election> getCompletedElections();

    Optional<Election> getCurrentActiveElection();

    boolean existsConflictingElection(Long electionId, LocalDateTime startTime, LocalDateTime endTime);

    Election updateElectionEndTime(Long electionId, LocalDateTime newEndTime);
}
