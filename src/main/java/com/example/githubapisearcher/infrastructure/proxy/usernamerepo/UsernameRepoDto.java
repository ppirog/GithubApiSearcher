package com.example.githubapisearcher.infrastructure.proxy.usernamerepo;

public record UsernameRepoDto(
        Long id,
        String name,
        Owner owner,
        Boolean fork
) {
}
