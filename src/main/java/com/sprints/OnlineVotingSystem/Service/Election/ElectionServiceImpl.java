package com.sprints.OnlineVotingSystem.Service.Election;


import com.sprints.OnlineVotingSystem.dto.request.ElectionDTO;
import com.sprints.OnlineVotingSystem.model.Election;
import com.sprints.OnlineVotingSystem.repository.ElectionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ElectionServiceImpl implements ElectionService{
        private final ElectionRepository electionRepository;

        public ElectionServiceImpl(ElectionRepository electionRepository) {
            this.electionRepository = electionRepository;
        }

        @Override
        public Election createElection(ElectionDTO electionDTO) {
            if (electionRepository.existsByTitle(electionDTO.getTitle())) {
                throw new IllegalArgumentException("Election with this title already exists.");
            }
            if (electionRepository.existsConflictingElection(
                    -1L, // New election, so no ID yet
                    electionDTO.getStartTime(),
                    electionDTO.getEndTime())) {
                throw new IllegalArgumentException("Election time conflicts with an existing election.");
            }

            Election election = new Election();
            election.setTitle(electionDTO.getTitle());
            election.setStartTime(electionDTO.getStartTime());
            election.setEndTime(electionDTO.getEndTime());

            return electionRepository.save(election);
        }

        @Override
        public Election updateElection(Long electionId, ElectionDTO electionDTO) {
            Election existing = electionRepository.findById(electionId)
                    .orElseThrow(() -> new EntityNotFoundException("Election not found"));

            if (electionRepository.existsConflictingElection(
                    electionId,
                    electionDTO.getStartTime(),
                    electionDTO.getEndTime())) {
                throw new IllegalArgumentException("Election time conflicts with another election.");
            }

            existing.setTitle(electionDTO.getTitle());
            existing.setStartTime(electionDTO.getStartTime());
            existing.setEndTime(electionDTO.getEndTime());

            return electionRepository.save(existing);
        }

        @Override
        public void deleteElection(Long electionId) {
            if (!electionRepository.existsById(electionId)) {
                throw new EntityNotFoundException("Election not found");
            }
            electionRepository.deleteById(electionId);
        }

        @Override
        public Optional<Election> getElectionById(Long electionId) {
            return electionRepository.findById(electionId);
        }

        @Override
        public List<Election> getAllElections() {
            return (List<Election>) electionRepository.findAll();
        }

        @Override
        public List<Election> getActiveElections() {
            return electionRepository.findActiveElections(LocalDateTime.now());
        }

        @Override
        public List<Election> getUpcomingElections() {
            return electionRepository.findUpcomingElections(LocalDateTime.now());
        }

        @Override
        public List<Election> getCompletedElections() {
            return electionRepository.findCompletedElections(LocalDateTime.now());
        }

        @Override
        public Optional<Election> getCurrentActiveElection() {
            return electionRepository.findCurrentActiveElection(LocalDateTime.now());
        }

        @Override
        public boolean existsConflictingElection(Long electionId, LocalDateTime startTime, LocalDateTime endTime) {
            return electionRepository.existsConflictingElection(electionId, startTime, endTime);
        }

        @Override
        public Election updateElectionEndTime(Long electionId, LocalDateTime newEndTime) {
            int updated = electionRepository.updateElectionEndTime(electionId, newEndTime);
            if (updated == 0) {
                throw new EntityNotFoundException("Election not found");
            }
            return electionRepository.findById(electionId)
                    .orElseThrow(() -> new EntityNotFoundException("Election not found after update"));
        }
    }


