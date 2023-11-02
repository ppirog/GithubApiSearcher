package com.example.githubapisearcher.branchesproxy;

public record GetBranchDto(
        String name,
        Commit commit
) {
}
