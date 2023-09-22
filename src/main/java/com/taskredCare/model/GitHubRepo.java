package com.taskredCare.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GitHubRepo {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("language")
    private String language;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("stargazers_count")
    private int stars;
    @JsonProperty("created_at")
    private String createdAt;

    public GitHubRepo(int id, String name, String language, String fullName, int stars, String createdAt) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.fullName = fullName;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
