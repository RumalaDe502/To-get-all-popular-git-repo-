package com.taskredCare.controller;


import com.taskredCare.model.GitHubRepo;
import com.taskredCare.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/popular-repos")
    public List<GitHubRepo> getPopularRepos(@RequestParam int size,
                                            @RequestParam String date) {
        return gitHubService.getPopularRepositories(size,date);
    }
}

