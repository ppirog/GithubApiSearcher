package com.example.githubapisearcher.infrastructure.restcontroller.dto.request;

public record CreateGithubRequestDto(
        String name,
        String login
) {
}
