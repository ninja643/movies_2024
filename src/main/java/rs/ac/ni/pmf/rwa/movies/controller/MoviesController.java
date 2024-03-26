package rs.ac.ni.pmf.rwa.movies.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MoviesController {

    @GetMapping("/")
    public List<MovieDTO> getMovies() {
        return List.of(MovieDTO.builder()
                        .id(1)
                        .name("The Gentlemen")
                        .genre(Genre.ACTION)
                        .releaseYear(2024)
                        .build(),
                MovieDTO.builder()
                        .id(2)
                        .name("The Sting")
                        .genre(Genre.ACTION)
                        .releaseYear(1972)
                        .build());
    }

    @GetMapping("/{movie_id}")
    public MovieDTO getMovie(@PathVariable(name = "movie_id", required = true) int id) {
        if (id == 1) {
            return MovieDTO.builder()
                    .id(1)
                    .name("The Gentlemen")
                    .genre(Genre.ACTION)
                    .releaseYear(2024)
                    .build();
        }

        if (id == 2) {
            return MovieDTO.builder()
                    .id(2)
                    .name("The Sting")
                    .genre(Genre.ACTION)
                    .releaseYear(1972)
                    .build();
        }

        throw new IllegalArgumentException("Movie with id: " + id + " does not exist");
    }

    @PostMapping("/")
    public void createMovie(@RequestBody MovieDTO movie) {
        log.info("Creating a new movie: {}", movie);

    }

    @DeleteMapping("/{movie_id}")
    public void deleteMovie(@PathVariable("movie_id") int id) {
        log.info("Delete movie with id {}", id);
    }

    @PutMapping("/{movie_id}")
    public void updateMovie(@PathVariable("movie_id") int id, @RequestBody MovieDTO updated) {
        log.info("Updating a movie with id {} with value {}", id, updated);
    }
}
