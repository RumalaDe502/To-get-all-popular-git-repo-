package com.taskredCare.service;

import com.taskredCare.dto.GitHubRepoResponse;
import com.taskredCare.model.GitHubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {
    private static final String GITHUB_API_URL = "https://api.github.com/search/repositories?q=created:2019-01-10&sort=stars&order=desc";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * The service provides:
     * A list of the most popular repositories, sorted by number of stars.
     * An option to be able to view the top 10, 50, 100 repositories should be available.
     * Given a date, the most popular repositories created from this date onwards should be returned.
     * @param size
     * @param date
     * @return
     */
    public List<GitHubRepo> getPopularRepositories(int size, String date) {
        ResponseEntity<GitHubRepoResponse> response = restTemplate.exchange(
                GITHUB_API_URL,
                HttpMethod.GET,
                null,
                GitHubRepoResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            GitHubRepoResponse responseBody = response.getBody();
            if (responseBody != null && responseBody.getItems() != null) {
                List<GitHubRepo> result = new ArrayList<>();
                for(GitHubRepo item: responseBody.getItems()) {
                    if(item.getCreatedAt().substring(0,10).equals(date)){
                        /* will be returning value from given date */
                        result = responseBody.getItems().stream().
                                limit(size)  // for view limit 10,50,100
                                .sorted(Comparator.comparing(GitHubRepo::getStars)) //sorted by stars
                                .collect(Collectors.toList());
                    }
                }
                return result;
            }
        }

        return Collections.emptyList();
    }
}

