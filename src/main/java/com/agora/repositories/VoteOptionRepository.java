package com.agora.repositories;

import com.agora.models.Issue;
import com.agora.models.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {
    List<VoteOption> findByIssue(Issue issue);
}
