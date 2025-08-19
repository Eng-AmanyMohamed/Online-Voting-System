package com.sprints.OnlineVotingSystem.Controller;

import com.sprints.OnlineVotingSystem.Service.User.UserService;
import com.sprints.OnlineVotingSystem.Service.Vote.VoteServiceImpl;
import com.sprints.OnlineVotingSystem.dto.request.VoteDTO;
import com.sprints.OnlineVotingSystem.model.User;
import com.sprints.OnlineVotingSystem.model.Vote;
import com.sprints.OnlineVotingSystem.util.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/elections/{electionId}/votes")
public class VoteController {

    private final VoteServiceImpl voteService;
    private final UserService userService;
    private final JwtService jwtService;

    public VoteController(VoteServiceImpl voteService, UserService userService, JwtService jwtService) {
        this.voteService = voteService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PreAuthorize("hasRole('Voter')")
    @PostMapping
    public ResponseEntity<?> castVote(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long electionId,
            @RequestBody @Valid VoteDTO voteRequest
    ) {
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        User user = userService.getUserByUsername(username);

        Optional<Vote> vote = voteService.vote(user.getUser_id(), voteRequest.getCandidateId(), electionId);

        return ResponseEntity.ok("Vote cast successfully");
    }

//
}
