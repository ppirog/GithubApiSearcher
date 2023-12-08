package com.example.githubapisearcher.infrastructure.restcontroller.dto.request;

public record PartiallyUpdateGithubRequestDto(
        String name,
        String login
) {
}
