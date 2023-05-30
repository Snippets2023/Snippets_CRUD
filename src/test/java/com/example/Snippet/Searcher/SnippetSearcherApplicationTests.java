package com.example.Snippet.Searcher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SnippetSearcherApplicationTests {

	private final int port = 8080;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void createSnippet() {
		String url = "http://localhost:" + port + "/add";

		Snippet snippet = new Snippet();
		snippet.setName("Snippet 2");
		snippet.setURL("http://nacho.com");
		snippet.setType("Type 2");
		snippet.setVersion("1.0");
		snippet.setValidate(true);
		snippet.setDate(new Date());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Snippet> request = new HttpEntity<>(snippet, headers);

		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, request, Void.class);

		// Add assertions for the response as needed
		// For example:
		// assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getSnippet() {
		String url = "http://localhost:" + port + "/find/{id}";

		// Replace {id} with the actual ID of the snippet you want to retrieve
		int snippetId = 1;
		url = url.replace("{id}", String.valueOf(snippetId));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Void> request = new HttpEntity<>(headers);

		ResponseEntity<Snippet> response = restTemplate.exchange(url, HttpMethod.GET, request, Snippet.class);

		// Add assertions for the response as needed
		// For example:
		// assertEquals(HttpStatus.OK, response.getStatusCode());
		// assertNotNull(response.getBody());
		// assertEquals("Snippet 1", response.getBody().getName());
	}

	@Test
	public void updateSnippet() {
		String url = "http://localhost:" + port + "/edit/{id}";

		// Replace {id} with the actual ID of the snippet you want to update
		int snippetId = 2;
		url = url.replace("{id}", String.valueOf(snippetId));

		Snippet snippet = new Snippet();
		snippet.setName("Updated Snippet");
		snippet.setURL("http://updated-example.com");
		snippet.setType("Updated Type");
		snippet.setVersion("2.0");
		snippet.setValidate(false);
		snippet.setDate(new Date());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Snippet> request = new HttpEntity<>(snippet, headers);

		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

		// Add assertions for the response as needed
		// For example:
		// assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deleteSnippet() {
		String url = "http://localhost:" + port + "/delete/{id}";

		// Replace {id} with the actual ID of the snippet you want to delete
		int snippetId = 1;
		url = url.replace("{id}", String.valueOf(snippetId));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Void> request = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class);

		// Add assertions for the response as needed
		// For example:
		// assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
