package com.nurvadli.movies.restfull.web.rest;

import com.nurvadli.movies.restfull.dto.MovieDto;
import com.nurvadli.movies.restfull.entity.Movie;
import com.nurvadli.movies.restfull.exception.RecordNotFoundException;
import com.nurvadli.movies.restfull.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.nurvadli.movies.restfull.utils.DateTimeUtil.toStringFormat;

/**
 * @author Nurvadli
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("")
    public ResponseEntity<Object> getMovies() {
        return new ResponseEntity<>(
                movieService.getAllMovies().stream().map(this::toMovieDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMoviesById(@PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id)
                .orElseThrow(() -> new RecordNotFoundException("Cannot find movie with id: " + id));
        return new ResponseEntity<>(
                toMovieDto(movie),
                HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@Valid @RequestBody MovieDto movieDto) {
        movieService.save(movieDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable Integer id, @RequestBody MovieDto movieDto) {
        movieDto.setId(id);
        movieService.update(movieDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable Integer id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private MovieDto toMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setRating(movie.getRating());
        movieDto.setImage(movie.getImage());
        movieDto.setCreatedAt(toStringFormat(movie.getCreatedAt()));
        movieDto.setUpdatedAt(toStringFormat(movie.getUpdatedAt()));
        return movieDto;
    }
}
