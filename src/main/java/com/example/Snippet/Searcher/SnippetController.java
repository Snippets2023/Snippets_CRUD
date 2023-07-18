package com.example.Snippet.Searcher;

import java.util.List;

import ast.AbstractSyntaxTree;
import exceptions.*;
import org.antlr.v4.runtime.Lexer;
import org.austral.edu.Exceptions.AssignationException;
import org.austral.edu.Exceptions.IncompatibilityException;
import org.austral.edu.Exceptions.InterpretException;
import org.austral.edu.Exceptions.NotDefinedException;
import org.austral.edu.InterpreterV1;
import org.austral.edu.LexerV1;
import org.austral.edu.Results.ClassicResult;
import org.austral.edu.Results.Result;
import org.austral.edu.StringInput;
import org.austral.edu.Token;
import org.springframework.web.bind.annotation.*;
import parser.ParserV1;

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

    @GetMapping("/run")
    String run(@RequestParam String id) throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, AssignationException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException {
        LexerV1 lexer = new LexerV1();
        ParserV1 parser = new ParserV1();
        SnippetResult result = new SnippetResult();
        InterpreterV1 interpreter = new InterpreterV1(result);

        List<Token> tokens = lexer.lex(new StringInput(id));

        AbstractSyntaxTree ast = parser.parse(tokens);

        interpreter.interpret(ast);

        return result.res;

    }

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
