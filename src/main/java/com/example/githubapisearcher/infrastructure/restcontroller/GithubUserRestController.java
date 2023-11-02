package com.example.githubapisearcher.infrastructure.restcontroller;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.model.exception.WrongExpectedDataFormatInHeaderException;
import com.example.githubapisearcher.domain.service.GithubMapper;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.response.GithubDataResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class GithubUserRestController {

    private final GithubMapper githubMapper;

    public GithubUserRestController(GithubMapper githubMapper) {
        this.githubMapper = githubMapper;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<GithubDataResponseDto>> getData(@PathVariable String username, @RequestHeader(value = "Accept") String header) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(header != null && header.equals("application/xml")){
            throw new WrongExpectedDataFormatInHeaderException("XML format is not available");
        } else if (header != null && header.equals("application/json")) {
            List<GithubData> githubData = githubMapper.mapFromUsernameToGithubData(username);

            List<GithubDataResponseDto> dataResponseDtos = githubMapper.mapFromGithubDataToGithubDataResponseDto(githubData);

            return new ResponseEntity<>(dataResponseDtos,httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>(List.of(),httpHeaders, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
