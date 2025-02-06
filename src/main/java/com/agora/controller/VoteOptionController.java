package com.agora.controller;

import com.agora.models.VoteOption;
import com.agora.service.VoteOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteOptionController {

    private final VoteOptionService voteOptionService;

    public VoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    /**
     * Creates a new VoteOption for the specified issue.
     *
     * @param issueId
     * @param voteOption
     * @return the VoteOption
     */
    @PostMapping("/issues/vote-options/{issueId}")
    public ResponseEntity<VoteOption> createVoteOption(
            @PathVariable Long issueId,
            @RequestBody VoteOption voteOption) {
        VoteOption createdOption = voteOptionService.createVoteOption(issueId, voteOption);
        return ResponseEntity.ok(createdOption);
    }

    /**
     * Retrieves all VoteOptions for the specified issue.
     *
     * @param issueId
     * @return
     */
    @GetMapping("/issues/vote-options/{issueId}")
    public ResponseEntity<List<VoteOption>> getVoteOptionsByIssue(@PathVariable Long issueId) {
        List<VoteOption> options = voteOptionService.getVoteOptionsByIssue(issueId);
        return ResponseEntity.ok(options);
    }

    /**
     * Updates an existing VoteOption.
     *
     * @param voteOptionId
     * @param updatedVoteOption
     * @return
     */
    @PutMapping("/vote-options/{voteOptionId}")
    public ResponseEntity<VoteOption> updateVoteOption(
            @PathVariable Long voteOptionId,
            @RequestBody VoteOption updatedVoteOption) {
        VoteOption option = voteOptionService.updateVoteOption(voteOptionId, updatedVoteOption);
        return ResponseEntity.ok(option);
    }

    /**
     * Deletes a VoteOption.
     *
     * @param voteOptionId
     * @return
     */
    @DeleteMapping("/vote-options/{voteOptionId}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable Long voteOptionId) {
        voteOptionService.deleteVoteOption(voteOptionId);
        return ResponseEntity.noContent().build();
    }
}
