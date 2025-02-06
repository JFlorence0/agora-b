package com.agora.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote_options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Label for the vote option (IE. "Yes", "No", or a candidate's name)
    @Column(nullable = false, length = 255)
    private String label;

    // Issue reference
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    // Optional ordering for the options
    @Column(name = "sort_order", columnDefinition = "integer default 0")
    private Integer sortOrder = 0;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Automatically set createdAt and updatedAt
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    // Automatically update the updatedAt
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
