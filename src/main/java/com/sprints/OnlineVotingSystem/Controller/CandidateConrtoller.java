package com.sprints.OnlineVotingSystem.Controller;

import com.sprints.OnlineVotingSystem.Service.Candidate.CandidateService;
import com.sprints.OnlineVotingSystem.dto.request.CandidateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/candidates")
public class CandidateConrtoller {

    private final CandidateService candidateService;

    public CandidateConrtoller(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CandidateDTO> addCandidate(@Valid @RequestBody CandidateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.addCandidate(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CandidateDTO> updateCandidate(@Valid @RequestBody CandidateDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(candidateService.updateCandidate(dto, id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CandidateDTO> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{election_id}")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    public ResponseEntity<List<CandidateDTO>> getAllCandidatesPerElection(@PathVariable long election_id) {
        return ResponseEntity.ok(candidateService.getAllCandidatesPerElection(election_id));
    }

}
