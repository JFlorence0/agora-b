package com.agora.service;

import com.agora.models.Issue;
import com.agora.models.User;
import com.agora.models.Vote;
import com.agora.models.VoteOption;
import com.agora.repositories.IssueRepository;
import com.agora.repositories.UserRepository;
import com.agora.repositories.VoteOptionRepository;
import com.agora.repositories.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final IssueRepository issueRepository;
    private final VoteOptionRepository voteOptionRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository,
                       IssueRepository issueRepository,
                       VoteOptionRepository voteOptionRepository,
                       UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.issueRepository = issueRepository;
        this.voteOptionRepository = voteOptionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Casts a vote on a given issue for the authenticated user.
     *
     * @param issueId
     * @param voteOptionId
     * @param userId
     * @return the Vote
     */
    @Transactional
    public Vote castVote(Long issueId, Long voteOptionId, Long userId) {
        // Fetch the issue
        Issue issue = issueRepository.findById(issueId)
            .orElseThrow(() -> new RuntimeException("Issue not found"));

        // Check if the issue is active for voting
        if (!issue.isActive()) {
            throw new RuntimeException("Issue is not active for voting");
        }

        // Fetch the vote option and ensure it belongs to the issue
        VoteOption voteOption = voteOptionRepository.findById(voteOptionId)
            .orElseThrow(() -> new RuntimeException("Vote Option not found"));
        if (!voteOption.getIssue().getId().equals(issueId)) {
            throw new RuntimeException("Vote Option does not belong to the given Issue");
        }

        // Fetch the user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has already voted on this issue
        Optional<Vote> existingVote = voteRepository.findByUserAndIssue(user, issue);
        if (existingVote.isPresent()) {
            throw new RuntimeException("User has already voted on this issue");
        }

        // Create and persist the vote
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setIssue(issue);
        vote.setVoteOption(voteOption);

        return voteRepository.save(vote);
    }

    /**
     * Updates an existing vote with a new vote option.
     *
     * @param voteId
     * @param newVoteOptionId
     * @param userId
     * @return the updated Vote
     */
    @Transactional
    public Vote updateVote(Long voteId, Long newVoteOptionId, Long userId) {
        // Retrieve the vote to update
        Vote vote = voteRepository.findById(voteId)
            .orElseThrow(() -> new RuntimeException("Vote not found"));

        // Ensure the vote belongs to the user
        if (!vote.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to update this vote");
        }

        // Retrieve the new vote option and ensure it belongs to the same issue
        VoteOption newVoteOption = voteOptionRepository.findById(newVoteOptionId)
            .orElseThrow(() -> new RuntimeException("Vote Option not found"));
        if (!newVoteOption.getIssue().getId().equals(vote.getIssue().getId())) {
            throw new RuntimeException("New Vote Option does not belong to the same issue");
        }

        // Update and persist the vote
        vote.setVoteOption(newVoteOption);
        return voteRepository.save(vote);
    }

    /**
     * Deletes a vote, ensuring that only the owner can remove it.
     *
     * @param voteId
     * @param userId
     */
    @Transactional
    public void deleteVote(Long voteId, Long userId) {
        Vote vote = voteRepository.findById(voteId)
            .orElseThrow(() -> new RuntimeException("Vote not found"));

        if (!vote.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to delete this vote");
        }
        voteRepository.delete(vote);
    }
}
