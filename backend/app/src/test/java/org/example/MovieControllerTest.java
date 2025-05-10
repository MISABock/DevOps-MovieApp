package org.example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock; // List importieren
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testGetMovies() {
        // Erstellen eines Testmovie
        Movie movie = new Movie("Inception");

        // Mocking der Methode, die die Liste der Filme zurückgibt
        Mockito.when(movieService.getAll()).thenReturn(List.of(movie));

        // Ausführen der Methode getMovies im Controller
        List<Movie> response = movieController.getMovies(); // Direkte Rückgabe der Liste

        // Überprüfen des ersten Films in der Liste
        assertEquals(1, response.size()); // Es gibt nur einen Film
        assertEquals("Inception", response.get(0).getTitle()); // Überprüfen des Filmtitels
    }
}

