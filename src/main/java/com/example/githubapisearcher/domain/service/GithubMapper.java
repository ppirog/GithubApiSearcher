package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.model.exception.WrongUsernameException;
import com.example.githubapisearcher.infrastructure.proxy.branch.BranchDto;
import com.example.githubapisearcher.infrastructure.proxy.branch.GithubBranchesProxy;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.response.GithubDataResponseDto;
import com.example.githubapisearcher.infrastructure.proxy.usernamerepo.UsernameRepoDto;
import com.example.githubapisearcher.infrastructure.proxy.usernamerepo.GithubUsernameProxy;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class GithubMapper {

    private final GithubUsernameProxy usernameProxy;
    private final GithubBranchesProxy branchesProxy;

    public GithubMapper(GithubUsernameProxy usernameProxy, GithubBranchesProxy branchesProxy) {
        this.usernameProxy = usernameProxy;
        this.branchesProxy = branchesProxy;
    }

    public List<GithubDataResponseDto> mapFromGithubDataToGithubDataResponseDto(List<GithubData> githubData) {
        List<GithubDataResponseDto> dataResponseDtos = new ArrayList<>();
        for (GithubData data : githubData) {
            dataResponseDtos.add(new GithubDataResponseDto(data.name(), data.login(), data.commitName(), data.lastCommitSha()));
        }
        return dataResponseDtos;
    }

    public List<GithubData> mapFromUsernameToGithubData(String username) {
        List<GithubData> githubData = new ArrayList<>();
        try {

            List<UsernameRepoDto> responseDtos = usernameProxy.fetchAllUsernameRepos(username)
                    .stream()
                    .filter(usernameRepoDto -> !usernameRepoDto.fork())
                    .toList();

            for (UsernameRepoDto g : responseDtos) {
                BranchDto lastCommitSha = branchesProxy.getBranches(g.owner().login(), g.name()).get(0);
                if (lastCommitSha != null) {
                    githubData.add(new GithubData(g.name(), g.owner().login(), lastCommitSha.name(), lastCommitSha.commit().sha()));
                } else {
                    githubData.add(new GithubData(g.name(), g.owner().login(), "", ""));
                }
            }

        } catch (FeignException e) {
            //TODO handle exception
            final String logMessage = "throw WrongUsernameException from " + this.getClass().getSimpleName();
            log.error(logMessage);
            final String clientMessage = "wrong username: " + username;
            throw new WrongUsernameException(clientMessage);
        }
        return githubData;
    }
}
