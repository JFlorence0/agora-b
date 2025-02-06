package com.agora.repositories;

import com.agora.models.Issue;
import com.agora.models.User;
import com.agora.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserAndIssue(User user, Issue issue);
}
