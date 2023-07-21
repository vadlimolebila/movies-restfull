package com.nurvadli.movies.restfull.repository;

import com.nurvadli.movies.restfull.entity.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurvadli
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private LocalDateTime mockDateTime = LocalDateTime.now();

    private OffsetDateTime toOffset(LocalDateTime localDateTime, int hours) {

        if (hours > 0) {
            localDateTime.plusHours(hours);
        }
        return localDateTime
                .atZone(ZoneId.of("Asia/Jakarta"))
                .toOffsetDateTime();
    }

    @Before
    public void before() {

        OffsetDateTime offsetDateTime1 = toOffset(mockDateTime, 0);
        OffsetDateTime offsetDateTime2 = toOffset(mockDateTime, 2);
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setTitle("movie 1");
        movie1.setDescription("description movie 1");
        movie1.setRating(7F);
        movie1.setImage("image 1");
        movie1.setCreatedAt(offsetDateTime1);
        movie1.setUpdatedAt(offsetDateTime1);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setTitle("movie 2");
        movie2.setDescription("description movie 2");
        movie2.setRating(4F);
        movie2.setImage("image 2");
        movie2.setCreatedAt(offsetDateTime2);
        movie2.setUpdatedAt(offsetDateTime2);
        movieRepository.save(movie2);
    }

    @Test
    public void testFindAll() {
        List<Movie> movies = movieRepository.findAll();
        assertEquals(2, movies.size());
    }

    @Test
    public void tesFindById() {
        Optional<Movie> movie = movieRepository.findById(2);
        assertEquals(2, movie.get().getId());
        assertEquals("movie 2", movie.get().getTitle());
        assertEquals("description movie 2", movie.get().getDescription());
        assertEquals(4F, movie.get().getRating());
        assertEquals("image 2", movie.get().getImage());
//        assertNotNull(movie.get().getCreatedAt());
//        assertNotNull(movie.get().getUpdatedAt());
    }

    @Test
    public void tesFindByTitle() {
        Optional<Movie> movie = movieRepository.findByTitle("movie 1");
        assertEquals(1, movie.get().getId());
        assertEquals("movie 1", movie.get().getTitle());
        assertEquals("description movie 1", movie.get().getDescription());
        assertEquals(7F, movie.get().getRating());
        assertEquals("image 1", movie.get().getImage());
        assertNotNull(movie.get().getCreatedAt());
//        assertNotNull(movie.get().getUpdatedAt());
    }

}
