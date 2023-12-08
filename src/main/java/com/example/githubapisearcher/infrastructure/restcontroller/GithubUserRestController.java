package com.example.githubapisearcher.infrastructure.restcontroller;

import com.example.githubapisearcher.domain.model.GithubData;
import com.example.githubapisearcher.domain.model.exception.WrongExpectedDataFormatInHeaderException;
import com.example.githubapisearcher.domain.service.*;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.request.CreateGithubRequestDto;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.request.PartiallyUpdateGithubRequestDto;
import com.example.githubapisearcher.infrastructure.restcontroller.dto.response.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
public class GithubUserRestController {

    private final GithubMapper githubMapper;
    private final GithubDataAdder githubDataAdder;
    private final GithubDataDeleter githubDataDeleter;
    private final GithubDataUpdater githubDataUpdater;
    private final GithubDataRetriever githubDataRetriever;


    @GetMapping(value = "/{username}")
    public ResponseEntity<List<GithubDataResponseDto>> getData(@PathVariable String username, @RequestHeader(value = "Accept") String header) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (header != null && header.equals("application/xml")) {
            throw new WrongExpectedDataFormatInHeaderException("XML format is not available");
        } else if (header != null && header.equals("application/json")) {
            List<GithubData> githubData = githubMapper.mapFromUsernameToGithubData(username);

            githubDataDeleter.deleteGithubDataByLogin(username);

            final List<GithubData> addedListGithubdata = githubDataAdder.addListGithubdata(githubData);

            List<GithubDataResponseDto> dataResponseDtos = githubMapper.mapFromGithubDataToGithubDataResponseDto(addedListGithubdata);

            return new ResponseEntity<>(dataResponseDtos, httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>(List.of(), httpHeaders, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @DeleteMapping(value = "/database/{username}")
    public ResponseEntity<List<DeletedGithubDataResponseDto>> deleteDataFromDatabase(@PathVariable String username) {

        final List<GithubData> deletedGithubDataByLogin = githubDataDeleter.deleteGithubDataByLogin(username);

        List<DeletedGithubDataResponseDto> dataResponseDtos = githubMapper.mapFromListOfGithubDataToListOfDeletedGithubDataResponseDto(deletedGithubDataByLogin);

        return new ResponseEntity<>(dataResponseDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/database")
    public ResponseEntity<List<GithubSelectedDataResponseDto>> getAllDataOnlyFromDatabase(@PageableDefault(page = 0, size = 5,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {

        List<GithubData> githubData = githubDataRetriever.findAll(pageable);

        List<GithubSelectedDataResponseDto> dataResponseDtos = githubMapper.mapFromGithubDataToGithubSelectedDataResponseDto(githubData);

        return new ResponseEntity<>(dataResponseDtos, HttpStatus.OK);
    }

    @PatchMapping(value = "/database/{id}")
    public ResponseEntity<GithubUpdatedDataResponseDto> updateDataInDatabase(@PathVariable @Valid Long id,
                                                                             @RequestBody PartiallyUpdateGithubRequestDto requestDto) {

        GithubData githubData = githubMapper.mapFromPartiallyUpdateGithubRequestDtoToGithubData(requestDto);

        githubDataUpdater.updatePartiallyById(id, githubData);

        GithubUpdatedDataResponseDto githubUpdatedDataResponseDto = githubMapper.mapFromGithubDataToGithubUpdatedDataResponseDto(githubData);

        return new ResponseEntity<>(githubUpdatedDataResponseDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GithubAddedDataResponseDto> updateDataInDatabase(@RequestBody CreateGithubRequestDto requestDto) {

        GithubData githubData = githubMapper.mapFromCreateGithubRequestDtoToGithubData(requestDto);

        githubDataAdder.addGithubData(githubData);

        GithubAddedDataResponseDto githubUpdatedDataResponseDto = githubMapper.mapFromGithubDataToGithubAddedDataResponseDto(githubData);

        return new ResponseEntity<>(githubUpdatedDataResponseDto, HttpStatus.OK);
    }


    @GetMapping(value = "/database/{username}")
    public ResponseEntity<List<GithubDataResponseDto>> getDataOnlyFromDatabase(@PathVariable String username) {

        List<GithubData> githubData = githubDataRetriever.findByLogin(username);

        List<GithubDataResponseDto> dataResponseDtos = githubMapper.mapFromGithubDataToGithubDataResponseDto(githubData);

        return new ResponseEntity<>(dataResponseDtos, HttpStatus.OK);
    }
}
