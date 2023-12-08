package com.example.githubapisearcher.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "repo")
@Entity
@ToString
public class GithubData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "owner")
    private String login;

    @Transient
    private String commitName;

    @Transient
    private String lastCommitSha;

    public GithubData(String name, String login, String commitName, String lastCommitSha) {
        this.name = name;
        this.login = login;
        this.commitName = commitName;
        this.lastCommitSha = lastCommitSha;
    }

}



