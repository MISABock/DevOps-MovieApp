package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
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
