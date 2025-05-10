package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testSaveAndFindMovie() {
        Movie movie = new Movie("Interstellar");
        movieRepository.save(movie);
        Movie foundMovie = movieRepository.findById(movie.getId()).orElse(null);

        assertNotNull(foundMovie);
        assertEquals("Interstellar", foundMovie.getTitle());
    }
}
