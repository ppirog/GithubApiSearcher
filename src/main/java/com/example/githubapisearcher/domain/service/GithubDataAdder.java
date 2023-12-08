package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.repository.GithubDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GithubDataAdder {
    private final GithubDataRepository githubDataRepository;

    public GithubData addGithubData(GithubData githubData) {
        log.info("added to database: " + githubData);
        githubDataRepository.save(githubData);
        return githubData;
    }

    public List<GithubData> addListGithubdata(List<GithubData> githubData) {
        githubData.forEach(this::addGithubData);
        return githubData;
    }
}
