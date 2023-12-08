package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.repository.GithubDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
@Log4j2
public class GithubDataDeleter {
    private final GithubDataRepository githubDataRepository;
    private final GithubDataRetriever githubDataRetriever;

    public void deleteGithubDataById(Long id) {

        githubDataRepository.findById(id);
        log.info("deleted GithubData: " + id);
        githubDataRepository.deleteById(id);
    }

    public List<GithubData> deleteGithubDataByLogin(String login) {

        final List<GithubData> byLoginFromDatabase = githubDataRetriever.findByLogin(login);

        if (!byLoginFromDatabase.isEmpty()) {
            log.info("deleted GithubData: " + login);
            githubDataRepository.deleteGithubDataByLogin(login);
        } else {
            //TODO handle exception when there is not login in db
            log.info("user: " + login + " not exists");
        }
        return byLoginFromDatabase;
    }
}
