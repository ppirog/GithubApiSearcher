package com.example.githubapisearcher.domain.repository;

import com.example.githubapisearcher.domain.model.GithubData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface GithubDataRepository extends Repository<GithubData, Long> {

    void save(GithubData githubData);

    List<GithubData> findAll(Pageable pageable);

    @Query("SELECT g from GithubData g WHERE g.id = :id")
    Optional<GithubData> findById(Long id);

    @Query("SELECT g from GithubData g WHERE g.login = :login")
    Optional<List<GithubData>> findByLogin(String login);

    @Modifying
    @Query("DELETE FROM GithubData s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("DELETE FROM GithubData s WHERE s.login = :login")
    void deleteGithubDataByLogin(String login);

    @Modifying
    @Query("UPDATE GithubData s SET s.name = :#{#updatedGithubData.name}, s.login = :#{#updatedGithubData.login} WHERE s.id = :id")
    void updateById(Long id, GithubData updatedGithubData);
}
