package com.agora.controller;

import com.agora.models.Vote;
import com.agora.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Casts a vote on a given issue.
     *
     * @param issueId
     * @param voteOptionId
     * @param userId
     * @return the Vote entity
     */
    @PostMapping("/issues/vote/{issueId}")
    public ResponseEntity<Vote> castVote(
            @PathVariable Long issueId,
            @RequestParam Long voteOptionId,
            @RequestParam Long userId) {
        Vote vote = voteService.castVote(issueId, voteOptionId, userId);
        return ResponseEntity.ok(vote);
    }

    /**
     * Updates an existing vote with a new vote option.
     *
     * @param voteId
     * @param newVoteOptionId
     * @param userId the voter
     * @return the updated vote
     */
    @PutMapping("/vote/{voteId}")
    public ResponseEntity<Vote> updateVote(
            @PathVariable Long voteId,
            @RequestParam Long newVoteOptionId,
            @RequestParam Long userId) {
        Vote vote = voteService.updateVote(voteId, newVoteOptionId, userId);
        return ResponseEntity.ok(vote);
    }

    /**
     * Deletes a vote.
     *
     * @param voteId
     * @param userId
     * @return void
     */
    @DeleteMapping("/vote/{voteId}")
    public ResponseEntity<Void> deleteVote(
            @PathVariable Long voteId,
            @RequestParam Long userId) {
        voteService.deleteVote(voteId, userId);
        return ResponseEntity.noContent().build();
    }
}
