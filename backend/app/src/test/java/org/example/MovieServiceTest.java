package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testAddMovie() {
        Movie movie = new Movie("The Dark Knight");
        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieService.add(movie);

        assertEquals("The Dark Knight", savedMovie.getTitle());
    }
}
