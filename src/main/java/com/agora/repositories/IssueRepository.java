package com.agora.repositories;

import com.agora.models.Issue;
import com.agora.models.enumerations.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    // Fetch all ACTIVE issues
    List<Issue> findByStatus(IssueStatus status);

    // Fetch issues by type (e.g., Laws, Elections)
    List<Issue> findByIssueType(com.agora.models.enumerations.IssueType issueType);

    // Fetch issues by region (Federal, State, etc.)
    List<Issue> findByRegion(com.agora.models.enumerations.IssueRegion region);

    // Fetch issues that are currently open for voting
    @Query("SELECT i FROM Issue i WHERE i.status = com.agora.models.enumerations.IssueStatus.ACTIVE AND (i.votingEndsAt IS NULL OR i.votingEndsAt > CURRENT_TIMESTAMP)")
    List<Issue> findOpenVotingIssues();


}
