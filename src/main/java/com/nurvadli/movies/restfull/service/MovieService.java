package com.nurvadli.movies.restfull.service;

import com.nurvadli.movies.restfull.dto.MovieDto;
import com.nurvadli.movies.restfull.entity.Movie;
import com.nurvadli.movies.restfull.exception.RecordAlreadyExistException;
import com.nurvadli.movies.restfull.exception.RecordNotFoundException;
import com.nurvadli.movies.restfull.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurvadli
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public void save(MovieDto movieDto) {
        Optional<Movie> existingMovie = movieRepository.findByTitle(movieDto.getTitle());
        if (existingMovie.isPresent()) {
            throw new RecordAlreadyExistException("Movie title '" + movieDto.getTitle() + "' has already exist");
        }

        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setRating(movieDto.getRating());
        movie.setImage(movieDto.getImage());
        movieRepository.save(movie);
    }

    public void update(MovieDto movieDto) {
        Movie movie = getMovieById(movieDto.getId()).orElseThrow(() -> new RecordNotFoundException("Cannot find movie with Id: " + movieDto.getId()));
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setRating(movieDto.getRating());
        movie.setImage(movieDto.getImage());
        movieRepository.save(movie);
    }

    public void delete(Integer id) {
        getMovieById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find movie with Id: " + id));

        movieRepository.deleteById(id);
    }
}
