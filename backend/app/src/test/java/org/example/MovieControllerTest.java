package org.example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class MovieControllerTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieController movieController;

    @Test
    public void testGetMovies() {
        // Beispielhafter Film wird vorher in einer Init-Methode oder per @Sql geladen – das ist hier nur symbolisch
        Movie movie = new Movie("Inception");
        movieService.add(movie); // fügt Testdaten ein

        List<Movie> response = movieController.getMovies();

        assertEquals(1, response.size());
        assertEquals("Inception", response.get(0).getTitle());
    }
}
