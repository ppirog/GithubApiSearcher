package com.example.githubapisearcher.infrastructure.usernameproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client")
public interface GithubUsernameProxy {
    //ALL
    //https://api.github.com/users/ppirog/repos

    //JUST ONE PER PAGE
    //https://api.github.com/users/ppirog/repos?per_page=1


    @GetMapping("/users/{username}/repos")
    List<GetSingleOneResponseDto> fetchAllSongs(@PathVariable String username);


}
