package com.example.Snippet.Searcher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SnippetRepository extends JpaRepository<Snippet, Long> {

}
