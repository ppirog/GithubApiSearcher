package com.example.githubapisearcher.infrastructure.proxy.branch;

public record BranchDto(
        String name,
        CommitDto commit
) {
}
