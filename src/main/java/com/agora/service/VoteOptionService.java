package com.agora.service;

import com.agora.models.Issue;
import com.agora.models.VoteOption;
import com.agora.repositories.IssueRepository;
import com.agora.repositories.VoteOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteOptionService {

    private final VoteOptionRepository voteOptionRepository;
    private final IssueRepository issueRepository;

    public VoteOptionService(VoteOptionRepository voteOptionRepository,
                             IssueRepository issueRepository) {
        this.voteOptionRepository = voteOptionRepository;
        this.issueRepository = issueRepository;
    }

    /**
     * Creates a new VoteOption for a given Issue.
     *
     * @param issueId    the ID of the Issue for which the VoteOption is being created
     * @param voteOption the VoteOption entity to be created
     * @return the persisted VoteOption
     */
    @Transactional
    public VoteOption createVoteOption(Long issueId, VoteOption voteOption) {
        // Fetch the issue
        Issue issue = issueRepository.findById(issueId)
            .orElseThrow(() -> new RuntimeException("Issue not found"));

        // Associate the vote option with the issue
        voteOption.setIssue(issue);
        return voteOptionRepository.save(voteOption);
    }

    /**
     * Retrieves all VoteOptions for a specific Issue.
     *
     * @param issueId the ID of the Issue
     * @return a list of VoteOptions for the Issue
     */
    public List<VoteOption> getVoteOptionsByIssue(Long issueId) {
        // Ensure the issue exists
        Issue issue = issueRepository.findById(issueId)
            .orElseThrow(() -> new RuntimeException("Issue not found"));

        return voteOptionRepository.findByIssue(issue);
    }

    /**
     * Updates an existing VoteOption.
     *
     * @param voteOptionId      the ID of the VoteOption to update
     * @param updatedVoteOption the updated VoteOption data
     * @return the updated VoteOption
     */
    @Transactional
    public VoteOption updateVoteOption(Long voteOptionId, VoteOption updatedVoteOption) {
        VoteOption voteOption = voteOptionRepository.findById(voteOptionId)
            .orElseThrow(() -> new RuntimeException("Vote Option not found"));

        voteOption.setLabel(updatedVoteOption.getLabel());
        voteOption.setSortOrder(updatedVoteOption.getSortOrder());
        // Update additional fields if necessary

        return voteOptionRepository.save(voteOption);
    }

    /**
     * Deletes a VoteOption.
     *
     * @param voteOptionId the ID of the VoteOption to delete
     */
    @Transactional
    public void deleteVoteOption(Long voteOptionId) {
        VoteOption voteOption = voteOptionRepository.findById(voteOptionId)
            .orElseThrow(() -> new RuntimeException("Vote Option not found"));
        voteOptionRepository.delete(voteOption);
    }
}
