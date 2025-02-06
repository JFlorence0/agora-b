package com.agora.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "issue_id"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user that voted
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // The issue voted on
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    // The selected vote option
    @ManyToOne(optional = false)
    @JoinColumn(name = "vote_option_id", nullable = false)
    private VoteOption voteOption;

    // Timestamp for when the initial vote was cast
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Timestamp for when the vote was last changed
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Automatically set createdAt and updatedAt fields
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    // Automatically update the updatedAt field before updating the entity
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}