package com.example.githubapisearcher;

import com.example.githubapisearcher.branchesproxy.GithubBranchesProxy;
import com.example.githubapisearcher.usernameproxy.GithubUsernameProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GithubApiSearcherApplication {

    private final GithubUsernameProxy usernameProxy;
    private final GithubBranchesProxy branchesProxy;

    public GithubApiSearcherApplication(GithubUsernameProxy usernameProxy, GithubBranchesProxy branchesProxy) {
        this.usernameProxy = usernameProxy;
        this.branchesProxy = branchesProxy;
    }

    public static void main(String[] args) {
        SpringApplication.run(GithubApiSearcherApplication.class, args);
    }
    @EventListener(ApplicationStartedEvent.class)
    public void run(){

        log.info(usernameProxy.fetchAllSongs("ppirog"));
        log.info(branchesProxy.getBranches("ppirog","FeignClient"));
    }
}
