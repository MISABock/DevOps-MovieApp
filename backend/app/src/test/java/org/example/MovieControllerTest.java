package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List; // List importieren

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        // Ausführen der Methode getMovies im Controller und Überprüfen des ResponseEntity
        ResponseEntity<List<Movie>> response = movieController.getMovies();

        // Überprüfen des Statuscodes und des Films im Body
        assertEquals(200, response.getStatusCodeValue()); // Statuscode 200 für OK
        assertEquals("Inception", response.getBody().get(0).getTitle()); // Überprüfen des Filmtitels
    }
}
