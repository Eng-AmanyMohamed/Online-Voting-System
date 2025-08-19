package com.sprints.OnlineVotingSystem.Controller;

import com.sprints.OnlineVotingSystem.Service.Vote.VoteServiceImpl;
import com.sprints.OnlineVotingSystem.dto.request.VoteDTO;
import com.sprints.OnlineVotingSystem.model.Vote;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/{id}/elections/{electionId}/votes")
public class VoteController {

    private final VoteServiceImpl voteService;

    public VoteController(VoteServiceImpl voteService) {
        this.voteService = voteService;
    }

    @PreAuthorize("hasRole('VOTER')")
    @PostMapping
    public ResponseEntity<?> castVote(
            @PathVariable Long id,
            @PathVariable Long electionId,
            @RequestBody @Valid VoteDTO voteRequest
    ) {
        Optional<Vote> vote = voteService.vote(id, voteRequest.getCandidateId(), electionId);

        return ResponseEntity.ok("Vote cast successfully for candidate: " + vote);
    }

//
}
