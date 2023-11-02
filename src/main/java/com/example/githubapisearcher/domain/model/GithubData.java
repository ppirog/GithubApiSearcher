package com.example.githubapisearcher.domain.model;

public record GithubData(
        String name,
        String login,
        String commitName,
        String lastCommitSha
) {
}
