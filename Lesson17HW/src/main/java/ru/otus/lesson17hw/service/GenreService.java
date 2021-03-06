package ru.otus.lesson17hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lesson17hw.domain.Genre;
import ru.otus.lesson17hw.repository.GenreReactiveRepository;

@Service
public class GenreService {

    @Autowired
    private GenreReactiveRepository genreRepository;
    @Autowired
    private ReactiveMongoOperations mongoOperations;

    private static final String ID = "_id";
    private static final String GENRE = "genre";

    public Flux<Genre> getGenres(){
        return genreRepository.findAll();
    }

    public Flux<Genre> deleteGenre(String id){
        return genreRepository.deleteById(id).thenMany(genreRepository.findAll()).cache();
    }

    public Mono<Genre> editGenre(Genre genre){
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(genre.getId()));
        Update update  = new Update();
        update.set(GENRE, genre.getGenre());
        return mongoOperations.updateFirst(query, update, Genre.class)
                .then(genreRepository.findById(genre.getId()))
                .cache();
    }

    public Flux<Genre> createNewGenre(Genre genre){
        return genreRepository.save(genre).thenMany(genreRepository.findAll()).cache();
    }
}