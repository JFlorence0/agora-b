package com.agora.service;

import com.agora.models.Issue;
import com.agora.models.enumerations.IssueStatus;
import com.agora.repositories.IssueRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // Get all issues
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // Get issue by ID
    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    // Get all ACTIVE issues
    public List<Issue> getActiveIssues() {
        return issueRepository.findByStatus(IssueStatus.ACTIVE);
    }

    // Get open voting issues
    public List<Issue> getOpenVotingIssues() {
        return issueRepository.findOpenVotingIssues();
    }

    // Create a new issue
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    // Update an existing issue
    public Optional<Issue> updateIssue(Long id, Issue updatedIssue) {
        return issueRepository.findById(id).map(existingIssue -> {
            existingIssue.setTitle(updatedIssue.getTitle());
            existingIssue.setDescription(updatedIssue.getDescription());
            existingIssue.setIssueType(updatedIssue.getIssueType());
            existingIssue.setStatus(updatedIssue.getStatus());
            existingIssue.setRegion(updatedIssue.getRegion());
            existingIssue.setReferenceId(updatedIssue.getReferenceId());
            existingIssue.setSponsor(updatedIssue.getSponsor());
            existingIssue.setTags(updatedIssue.getTags());
            existingIssue.setVotingStartsAt(updatedIssue.getVotingStartsAt());
            existingIssue.setVotingEndsAt(updatedIssue.getVotingEndsAt());
            return issueRepository.save(existingIssue);
        });
    }

    // Delete an issue
    public boolean deleteIssue(Long id) {
        if (issueRepository.existsById(id)) {
            issueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}