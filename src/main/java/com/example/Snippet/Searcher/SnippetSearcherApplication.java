package com.example.Snippet.Searcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SnippetSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetSearcherApplication.class, args);
	}

}
