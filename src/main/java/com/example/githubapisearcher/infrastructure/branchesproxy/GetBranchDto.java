package com.example.githubapisearcher.infrastructure.branchesproxy;

public record GetBranchDto(
        String name,
        CommitDto commit
) {
}
