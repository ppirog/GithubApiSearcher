package com.example.githubapisearcher.infrastructure.proxy.usernamerepo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client")
public interface GithubUsernameProxy {
    //ALL
    //https://api.github.com/users/{username}/repos

    @GetMapping("/users/{username}/repos")
    List<UsernameRepoDto> fetchAllUsernameRepos(@PathVariable String username);

}
