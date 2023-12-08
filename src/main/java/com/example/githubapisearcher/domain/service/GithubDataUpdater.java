package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.repository.GithubDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Transactional
@Service
@AllArgsConstructor
@Log4j2
public class GithubDataUpdater {
    private final GithubDataRetriever githubDataRetriever;
    private final GithubDataRepository githubDataRepository;


    public void updateById(Long id, GithubData githubData) {
        final GithubData byId = githubDataRetriever.findById(id);
        githubDataRepository.updateById(id, githubData);
    }

    public GithubData updatePartiallyById(Long id, GithubData githubData) {

        GithubData fromDatabaseGithubData = githubDataRetriever.findById(id);

        GithubData.GithubDataBuilder builder = GithubData.builder();

        builder.name(fromDatabaseGithubData.getName());
        builder.login(fromDatabaseGithubData.getLogin());
        builder.commitName(fromDatabaseGithubData.getCommitName());
        builder.lastCommitSha(fromDatabaseGithubData.getLastCommitSha());

        if (githubData.getName() != null) {
            builder.name(githubData.getName());
        }

        if (githubData.getLogin() != null) {
            builder.login(githubData.getLogin());
        }

        if (githubData.getCommitName() != null) {
            builder.login(githubData.getCommitName());
        }

        if (githubData.getLastCommitSha() != null) {
            builder.login(githubData.getLastCommitSha());
        }

        final GithubData updatedGithubdata = builder.build();
        updateById(id,updatedGithubdata);

        return updatedGithubdata;
    }

}
