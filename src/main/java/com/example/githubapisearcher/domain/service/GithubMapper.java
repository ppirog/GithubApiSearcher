package com.example.githubapisearcher.domain.service;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.model.exception.WrongUsernameException;
import com.example.githubapisearcher.infrastructure.proxy.branch.BranchDto;
import com.example.githubapisearcher.infrastructure.proxy.branch.GithubBranchesProxy;
import com.example.githubapisearcher.infrastructure.proxy.usernamerepo.GithubUsernameProxy;
import com.example.githubapisearcher.infrastructure.proxy.usernamerepo.UsernameRepoDto;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.request.CreateGithubRequestDto;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.request.PartiallyUpdateGithubRequestDto;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.response.*;
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
            dataResponseDtos.add(new GithubDataResponseDto(data.getName(), data.getLogin(), data.getCommitName(), data.getLastCommitSha()));
        }
        return dataResponseDtos;
    }

    public List<GithubSelectedDataResponseDto> mapFromGithubDataToGithubSelectedDataResponseDto(List<GithubData> githubData) {
        List<GithubSelectedDataResponseDto> dataResponseDtos = new ArrayList<>();
        for (GithubData data : githubData) {
            dataResponseDtos.add(new GithubSelectedDataResponseDto(data.getName(), data.getLogin()));
        }
        return dataResponseDtos;
    }

    public GithubData mapFromPartiallyUpdateGithubRequestDtoToGithubData(PartiallyUpdateGithubRequestDto requestDto) {
        return new GithubData(requestDto.name(), requestDto.login(), null, null);
    }

    public GithubUpdatedDataResponseDto mapFromGithubDataToGithubUpdatedDataResponseDto(GithubData githubData) {
        return new GithubUpdatedDataResponseDto(githubData.getName(), githubData.getLogin());
    }

    public List<GithubData> mapFromUsernameToGithubData(String username) {
        List<GithubData> githubData = new ArrayList<>();
        try {

            List<UsernameRepoDto> responseDtos = usernameProxy.fetchAllUsernameRepos(username).stream().filter(usernameRepoDto -> !usernameRepoDto.fork()).toList();

            for (UsernameRepoDto g : responseDtos) {
                BranchDto lastCommitSha = branchesProxy.getBranches(g.owner().login(), g.name()).get(0);
                if (lastCommitSha != null) {
                    githubData.add(new GithubData(g.name(), g.owner().login(), lastCommitSha.name(), lastCommitSha.commit().sha()));
                } else {
                    githubData.add(new GithubData(g.name(), g.owner().login(), "", ""));
                }
            }

        } catch (FeignException e) {
            final String logMessage = "throw WrongUsernameException from " + this.getClass().getSimpleName();
            log.error(logMessage);
            final String clientMessage = "wrong username: " + username;
            throw new WrongUsernameException(clientMessage);
        }
        return githubData;
    }

    private DeletedGithubDataResponseDto mapFromGithubDataToDeletedGithubDataResponseDto(GithubData githubData) {
        return new DeletedGithubDataResponseDto(githubData.getName(), githubData.getLogin());
    }

    public List<DeletedGithubDataResponseDto> mapFromListOfGithubDataToListOfDeletedGithubDataResponseDto(List<GithubData> githubData) {
        return githubData.stream().map(this::mapFromGithubDataToDeletedGithubDataResponseDto).toList();
    }

    public GithubData mapFromCreateGithubRequestDtoToGithubData(CreateGithubRequestDto requestDto) {
        return new GithubData(requestDto.name(),requestDto.login(),null,null);
    }

    public GithubAddedDataResponseDto mapFromGithubDataToGithubAddedDataResponseDto(GithubData githubData) {
        return new GithubAddedDataResponseDto(githubData.getName(),githubData.getLogin());
    }
}
