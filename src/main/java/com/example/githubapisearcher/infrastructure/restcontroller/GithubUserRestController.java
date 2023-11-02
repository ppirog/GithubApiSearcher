package com.example.githubapisearcher.infrastructure.restcontroller;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.response.GithubDataResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class GithubUserRestController {

    private final GithubMapper githubMapper;

    public GithubUserRestController(GithubMapper githubMapper) {
        this.githubMapper = githubMapper;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<GithubDataResponseDto>> getData(@PathVariable String username) {

        List<GithubData> githubData = githubMapper.mapFromUsernameToGithubData(username);

        List<GithubDataResponseDto> dataResponseDtos = githubMapper.mapFromGithubDataToGithubDataResponseDto(githubData);

        return ResponseEntity.ok(dataResponseDtos);

    }

}
