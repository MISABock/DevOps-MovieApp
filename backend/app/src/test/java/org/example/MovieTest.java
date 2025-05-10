package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MovieTest {

    @Test
    public void testMovieCreation() {
        Movie movie = new Movie("The Matrix");

        assertEquals("The Matrix", movie.getTitle());
    }
}
