package com.example.githubapisearcher.infrastructure.usernameproxy;

public record GetSingleOneResponseDto(
        Long id,
        String name,
        Owner owner,
        Boolean fork
) {
}
