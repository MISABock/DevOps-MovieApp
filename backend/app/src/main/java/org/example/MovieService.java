package org.example;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAll() {
        return repository.findAll();
    }

    public Movie add(Movie movie) {
        return repository.save(movie);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
