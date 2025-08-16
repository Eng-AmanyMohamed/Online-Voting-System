package com.sprints.OnlineVotingSystem.Controller;


import com.sprints.OnlineVotingSystem.Service.Election.ElectionService;
import com.sprints.OnlineVotingSystem.dto.request.ElectionDTO;
import com.sprints.OnlineVotingSystem.model.Election;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    //Admin

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Election> createElection(@Valid @RequestBody ElectionDTO electionDTO) {
        Election savedElection = electionService.createElection(electionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedElection);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Election> updateElection(@PathVariable Long id,
                                                   @Valid @RequestBody ElectionDTO electionDTO) {
        Election updatedElection = electionService.updateElection(id, electionDTO);
        return ResponseEntity.ok(updatedElection);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/end-time")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Election> updateElectionEndTime(@PathVariable Long id,
                                                          @RequestParam LocalDateTime newEndTime) {
        Election updatedElection = electionService.updateElectionEndTime(id, newEndTime);
        return ResponseEntity.ok(updatedElection);
    }

    // Admin & Voter

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<Election> getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<List<Election>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }

    @GetMapping("/active")
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<List<Election>> getActiveElections() {
        return ResponseEntity.ok(electionService.getActiveElections());
    }

    @GetMapping("/upcoming")
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<List<Election>> getUpcomingElections() {
        return ResponseEntity.ok(electionService.getUpcomingElections());
    }

    @GetMapping("/completed")
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<List<Election>> getCompletedElections() {
        return ResponseEntity.ok(electionService.getCompletedElections());
    }

    @GetMapping("/current")
    @PreAuthorize("hasAnyRole('ADMIN','VOTER')")
    public ResponseEntity<Election> getCurrentActiveElection() {
        return electionService.getCurrentActiveElection()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}
