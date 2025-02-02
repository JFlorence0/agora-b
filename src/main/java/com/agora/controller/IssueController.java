package com.agora.controller;

import com.agora.models.Issue;
import com.agora.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // 1️⃣ Fetch all issues
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    // 2️⃣ Fetch issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ Fetch active issues
    @GetMapping("/active")
    public ResponseEntity<List<Issue>> getActiveIssues() {
        return ResponseEntity.ok(issueService.getActiveIssues());
    }

    // 4️⃣ Fetch open voting issues
    @GetMapping("/open-voting")
    public ResponseEntity<List<Issue>> getOpenVotingIssues() {
        return ResponseEntity.ok(issueService.getOpenVotingIssues());
    }

    // 5️⃣ Create a new issue
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(createdIssue);
    }

    // 6️⃣ Update an issue
    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue updatedIssue) {
        return issueService.updateIssue(id, updatedIssue)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 7️⃣ Delete an issue
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        return issueService.deleteIssue(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
