package com.taskredCare.service;

import com.taskredCare.dto.GitHubRepoResponse;
import com.taskredCare.model.GitHubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * The service provides:
     * A list of the most popular repositories, sorted by number of stars.
     * An option to be able to view the top 10, 50, 100 repositories should be available.
     * Given a date, the most popular repositories created from this date onwards should be returned.
     * @param size no of views
     * @param date given date
     * @param language programming language
     * @return list of items
     */
    public List<GitHubRepo> getPopularRepositories(int size, String date, String language) {
        //repo from given date
        String GITHUB_API_URL = "https://api.github.com/search/repositories?q=created:>" +date+
                "&sort=stars&order=desc";
        ResponseEntity<GitHubRepoResponse> response = restTemplate.exchange(
                GITHUB_API_URL,
                HttpMethod.GET,
                null,
                GitHubRepoResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            GitHubRepoResponse responseBody = response.getBody();
            if (responseBody != null && responseBody.getItems() != null) {
                        return responseBody.getItems().stream()
                                .limit(size)  // for view limit 10,50,100
                                .filter(itm -> itm.getLanguage() != null && itm.getLanguage().equalsIgnoreCase(language)) // filter by programming language
                                .sorted(Comparator.comparing(GitHubRepo::getStars).reversed()) //sorted by stars
                                .collect(Collectors.toList());

            }
        }
        return Collections.emptyList();
    }
}

