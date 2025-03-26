package com.agora.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import com.agora.models.enumerations.*;

@Entity
@Table(name = "issues")
@Getter
@Setter
@AllArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueRegion region;

    @Column(length = 100, unique = true)
    private String referenceId;

    @Column(length = 255)
    private String sponsor;

    @ManyToMany
    @JoinTable(
        name = "issue_tags",
        joinColumns = @JoinColumn(name = "issue_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    private LocalDateTime votingStartsAt;
    private LocalDateTime votingEndsAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructor to initialize createdAt automatically
    public Issue() {
        this.createdAt = LocalDateTime.now();
    }

    // Improved isActive() logic
    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return this.status == IssueStatus.ACTIVE &&
               (this.votingStartsAt == null || !this.votingStartsAt.isAfter(now)) &&
               (this.votingEndsAt == null || this.votingEndsAt.isAfter(now));
    }
}

