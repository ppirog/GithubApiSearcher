package com.example.githubapisearcher.usernameproxy;

public record GetSingleOneResponseDto(
        Long id,
        String name,
        Owner owner,
        Boolean fork
) {
}
