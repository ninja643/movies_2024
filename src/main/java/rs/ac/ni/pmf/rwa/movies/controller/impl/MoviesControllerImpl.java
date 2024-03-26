package rs.ac.ni.pmf.rwa.movies.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.movies.controller.MoviesController;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.List;

@Slf4j
@RestController
public class MoviesControllerImpl implements MoviesController {

    @Override
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

    @Override
    public MovieDTO getMovie(int id) {
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

    @Override
    public void createMovie(MovieDTO movie) {
        log.info("Creating a new movie: {}", movie);

    }

    @Override
    public void deleteMovie(int id) {
        log.info("Delete movie with id {}", id);
    }

    @Override
    public void updateMovie(int id, @RequestBody MovieDTO updated) {
        log.info("Updating a movie with id {} with value {}", id, updated);
    }
}
