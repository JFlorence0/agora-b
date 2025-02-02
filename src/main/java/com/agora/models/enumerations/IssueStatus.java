package com.agora.models.enumerations;

public enum IssueStatus {
    DRAFT,      // Before it's proposed
    ACTIVE,     // Open for voting
    CLOSED,     // Voting ended
    PASSED,     // Became law or was approved
    FAILED,     // Rejected or did not pass
    FINALIZED   // Used for elections after results are in
}
