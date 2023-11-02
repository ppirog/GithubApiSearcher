package com.example.githubapisearcher.infrastructure.proxy.branch;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client-branch")
public interface GithubBranchesProxy {

    //https://api.github.com/repos/OWNER/REPO/branches

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getBranches(@PathVariable String owner, @PathVariable String repo);

}
