package com.nurvadli.movies.restfull.service;

import com.nurvadli.movies.restfull.dto.MovieDto;
import com.nurvadli.movies.restfull.entity.Movie;
import com.nurvadli.movies.restfull.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Nurvadli
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Captor
    private ArgumentCaptor<Movie> movieArgumentCaptor;

    List<Movie> movies;

     LocalDateTime mockDateTime;

    @Before
    public void setUp() {
        movies = new ArrayList<>();
        mockDateTime = LocalDateTime.now();
        OffsetDateTime offsetDateTime1 = toOffset(mockDateTime, 0);
        OffsetDateTime offsetDateTime2 = toOffset(mockDateTime, 2);

        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setTitle("Jhon Wick");
        movie1.setDescription("Jhon Wick never die til all enemies down!");
        movie1.setRating(7F);
        movie1.setImage("image");
        movie1.setCreatedAt(offsetDateTime1);
        movie1.setUpdatedAt(offsetDateTime1);
        movies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setTitle("Insidious 3");
        movie2.setDescription("Anna has possessed by deamon, and Jhonah as a Pastor wan't able do leave!");
        movie2.setRating(4F);
        movie2.setImage("image");
        movie2.setCreatedAt(offsetDateTime2);
        movie2.setUpdatedAt(offsetDateTime2);
        movies.add(movie2);
    }

    private OffsetDateTime toOffset(LocalDateTime localDateTime, int hours) {
        if(hours>0) {
            localDateTime.plusHours(hours);
        }
        return localDateTime
                .atZone(ZoneId.of("Asia/Jakarta"))
                .toOffsetDateTime();
    }

    @Test
    public void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(movies);
       List<Movie> result = movieService.getAllMovies();

        verify(movieRepository, times(1)).findAll();
        assertFalse(result.isEmpty());
        assertEquals("Insidious 3",  result.get(1).getTitle());
        assertEquals("Anna has possessed by deamon, and Jhonah as a Pastor wan't able do leave!",  result.get(1).getDescription());
        assertEquals(4F,  result.get(1).getRating());
        assertEquals("image",  result.get(1).getImage());
    }

    @Test
    public void testGetMoviesById() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movies.get(0)));
        Movie result = movieService.getMovieById(1).get();

        verify(movieRepository, times(1)).findById(1);
        assertEquals("Jhon Wick",  result.getTitle());
        assertEquals("Jhon Wick never die til all enemies down!",  result.getDescription());
        assertEquals(7F,  result.getRating());
        assertEquals("image",  result.getImage());
    }

    @Test
    public void testGetMoviesById_throwRecordNotFound() {
        when(movieRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Movie> result= movieService.getMovieById(1);

        verify(movieRepository, times(1)).findById(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Pengabdi Setan");
        movieDto.setDescription("description of Pengabdi setan");
        movieDto.setRating(6F);
        movieDto.setImage("image");
        movieDto.setCreatedAt("2022-08-01 10:56:31");
        movieDto.setCreatedAt("2022-08-13 09:30:23");
        when(movieRepository.findByTitle("Pengabdi Setan")).thenReturn(Optional.empty());
        movieService.save(movieDto);

        verify(movieRepository, times(1)).save(movieArgumentCaptor.capture());
        Movie movie = movieArgumentCaptor.getValue();
        assertEquals(movieDto.getTitle(),  movie.getTitle());
        assertEquals(movieDto.getDescription(),  movie.getDescription());
        assertEquals(movieDto.getRating(),  movie.getRating());
        assertEquals(movieDto.getImage(),  movie.getImage());
    }

    @Test
    public void testUpdate() {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(1);
        movieDto.setTitle("Jhon Wick III");
        movieDto.setDescription("Jhon Wick never die til all enemies down!");
        movieDto.setRating(7F);
        movieDto.setImage("image");
        movieDto.setCreatedAt("2023-08-01 10:56:31");
        movieDto.setCreatedAt("2023-08-13 09:30:23");
        when(movieRepository.findById(1)).thenReturn(Optional.of(movies.get(0)));
        movieService.update(movieDto);

        verify(movieRepository, times(1)).save(movieArgumentCaptor.capture());
        Movie movie = movieArgumentCaptor.getValue();
        assertEquals(movieDto.getTitle(),  movie.getTitle());
        assertEquals(movieDto.getDescription(),  movie.getDescription());
        assertEquals(movieDto.getRating(),  movie.getRating());
        assertEquals(movieDto.getImage(),  movie.getImage());
    }

    @Test
    public void testDelete() {
        when(movieRepository.findById(2)).thenReturn(Optional.of(movies.get(1)));
        movieService.delete(2);

        verify(movieRepository, times(1)).deleteById(2);
    }
}
