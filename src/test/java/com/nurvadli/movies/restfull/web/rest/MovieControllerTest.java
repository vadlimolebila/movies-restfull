package com.nurvadli.movies.restfull.web.rest;

import com.nurvadli.movies.restfull.entity.Movie;
import com.nurvadli.movies.restfull.service.MovieService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Nurvadli
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {


    private MockMvc mockMvc;

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    List<Movie> movies;

    LocalDateTime mockDateTime;

    private OffsetDateTime toOffset(LocalDateTime localDateTime, int hours) {
        if(hours>0) {
            localDateTime.plusHours(hours);
        }
        return localDateTime
                .atZone(ZoneId.of("Asia/Jakarta"))
                .toOffsetDateTime();
    }
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

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

    @Test
    public void testGetAllMovies() throws Exception {
        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Jhon Wick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Jhon Wick never die til all enemies down!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(7F))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].image").value("image"));

    }

    @Test
    public void testGetMovieById() throws Exception {
        when(movieService.getMovieById(2)).thenReturn(Optional.of(movies.get(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Insidious 3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Anna has possessed by deamon, and Jhonah as a Pastor wan't able do leave!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(4F))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("image"));
    }

    @Test
    public  void testSave() throws Exception {
        JSONObject request = new JSONObject();
        request.put("title", "Film Pendidikan");
        request.put("description", "for student");
        request.put("rating", 7);
        request.put("image", "");
        request.put("createdAt", "2023-01-01 02:10:56");
        request.put("updatedAt", "2023-01-05 13:07:01");

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(movieService, times(1)).save(any());
    }

    @Test
    public void testUpdate() throws Exception {
        JSONObject request = new JSONObject();
        request.put("title", "Film Mendidik");
        request.put("description", "for student");
        request.put("rating", 8);
        request.put("image", "/anImageUrl");
        request.put("createdAt", "2023-01-01 02:10:56");
        request.put("updatedAt", "2023-01-05 13:06:01");

        mockMvc.perform(MockMvcRequestBuilders.put("/movies/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request.toString()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

        verify(movieService, times(1)).update(any());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

        verify(movieService, times(1)).delete(any());
    }

}
