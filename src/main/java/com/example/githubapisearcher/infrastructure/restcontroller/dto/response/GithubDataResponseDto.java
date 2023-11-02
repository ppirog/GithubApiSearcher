package com.example.githubapisearcher.infrastructure.restcontroller.dto.response;

public record GithubDataResponseDto(
        String name,
        String login,
        String commitName,
        String lastCommitSha
        ) {
}
