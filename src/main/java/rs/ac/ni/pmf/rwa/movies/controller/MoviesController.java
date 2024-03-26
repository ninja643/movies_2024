package rs.ac.ni.pmf.rwa.movies.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;

import java.util.List;

@RequestMapping("/api/v1/movies")
public interface MoviesController {
    @GetMapping("")
    List<MovieDTO> getMovies();

    @GetMapping("/{movie_id}")
    MovieDTO getMovie(@PathVariable(name = "movie_id") int id);

    @PostMapping("")
    void createMovie(@RequestBody MovieDTO movie);

    @DeleteMapping("/{movie_id}")
    void deleteMovie(@PathVariable("movie_id") int id);

    @PutMapping("/{movie_id}")
    void updateMovie(@PathVariable("movie_id") int id, @RequestBody MovieDTO updated);
}
