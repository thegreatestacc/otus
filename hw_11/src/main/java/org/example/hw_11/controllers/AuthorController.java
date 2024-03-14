package org.example.hw_11.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.author.AuthorDto;
import org.example.hw_11.services.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public Mono<AuthorDto> getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }
}
