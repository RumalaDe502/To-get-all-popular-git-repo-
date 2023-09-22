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
    GitHubService gitHubService;

    /**
     * This is controller to get popular git repositories from given date and no.of gits to be returned.
     * @param size no.of gits to be returned
     * @param date given date
     * @return
     */
    @GetMapping("/popular-repos")
    public List<GitHubRepo> getPopularRepos(@RequestParam int size,
                                            @RequestParam String date,
                                            @RequestParam String language) {
        return gitHubService.getPopularRepositories(size,date,language);
    }
}

