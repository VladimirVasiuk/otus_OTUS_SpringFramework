package ru.otus.lesson17hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lesson17hw.domain.Genre;
import ru.otus.lesson17hw.service.GenreService;

//@RestController
//@RequestMapping("/genres")
public class GenreRestController {

//    @Autowired
//    private GenreService genreService;
//
//    @PostMapping
//    public @ResponseBody Flux<Genre> getGenres(){
//        return Flux.fromStream(genreService.getGenres().stream());
//    }
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody Flux<Genre> deleteGenre(@PathVariable String id){
//        return Flux.fromStream(genreService.deleteGenre(id).stream());
//    }
//    @PutMapping
//    public @ResponseBody
//    Mono<Genre> editGenre(@RequestBody Genre genre){
//        return Mono.create(value -> genreService.editGenre(genre));
//    }
//
//    @PostMapping("/createNewGenre")
//    public @ResponseBody Flux<Genre> createNewGenre(@RequestBody Genre genre){
//        return Flux.fromStream(genreService.createNewGenre(genre).stream());
//    }
}