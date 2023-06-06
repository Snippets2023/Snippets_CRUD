package com.example.Snippet.Searcher;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

class SnippetController {

    private final SnippetRepository repository;

    SnippetController(SnippetRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/getAll")
    List<Snippet> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/add")
    Snippet newSnippet(@RequestBody Snippet newSnippet) {
        return repository.save(newSnippet);
    }

    // Single item

    @GetMapping("/find/{id}")
    Snippet one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find snippet " + id));
    }

    @PutMapping("/edit/{id}")
    Snippet replaceSnippet(@RequestBody Snippet newSnippet, @PathVariable Long id) {

        return repository.findById(id)
                .map(snippet -> {
                    snippet.setName(newSnippet.getName());
                    snippet.setURL(newSnippet.getURL());
                    snippet.setType(newSnippet.getType());
                    snippet.setVersion(newSnippet.getVersion());
                    snippet.setValidate(newSnippet.isValidate());
                    snippet.setDate(newSnippet.getDate());
                    return repository.save(snippet);
                })
                .orElseGet(() -> {
                    newSnippet.setId(id);
                    return repository.save(newSnippet);
                });
    }

    @DeleteMapping("/delete/{id}")
    void deleteSnippet(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
