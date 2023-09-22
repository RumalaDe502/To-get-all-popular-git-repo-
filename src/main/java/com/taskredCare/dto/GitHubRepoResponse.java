package com.taskredCare.dto;

import com.taskredCare.model.GitHubRepo;
import lombok.Data;

import java.util.List;

@Data
public class GitHubRepoResponse {
    private List<GitHubRepo> items;
}
