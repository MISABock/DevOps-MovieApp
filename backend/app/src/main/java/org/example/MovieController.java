package org.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final List<Movie> movieList = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Movie> getMovies() {
        return movieList;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        movie.setId(nextId++);
        movieList.add(movie);
        return movie;
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieList.removeIf(m -> m.getId().equals(id));
    }


}
