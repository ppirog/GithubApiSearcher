package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.model.exception.GithubDataNotFoundInDatabaseException;
import com.example.githubapisearcher.domain.repository.GithubDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GithubDataRetriever {

    private final GithubDataRepository githubDataRepository;

    public List<GithubData> findAll(Pageable pageable) {
        log.info("retriving all data from table github_data");
        return githubDataRepository
                .findAll(pageable);
    }

    public GithubData findById(Long id) {
        return githubDataRepository
                .findById(id)
                .orElseThrow(() ->
                        new GithubDataNotFoundInDatabaseException("not found " + id)
                );
    }

    public List<GithubData> findByLogin(String login) {

        final List<GithubData> byLoginFromDatabase = githubDataRepository
                .findByLogin(login)
                .get();

        if (byLoginFromDatabase.isEmpty()) {
            throw new GithubDataNotFoundInDatabaseException("login" + login + " not found in database");
        }
        return byLoginFromDatabase;
    }
}
