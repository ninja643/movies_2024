package rs.ac.ni.pmf.rwa.movies.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.movies.controller.MoviesController;
import rs.ac.ni.pmf.rwa.movies.exception.MovieNotFoundException;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.model.dto.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.model.mapper.MoviesMapper;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MoviesControllerImpl implements MoviesController {

    private final MoviesRepository moviesRepository;

    private final MoviesMapper moviesMapper;

    public MoviesControllerImpl(
            MoviesRepository moviesRepository,
            MoviesMapper moviesMapper) {
        this.moviesRepository = moviesRepository;
        this.moviesMapper = moviesMapper;
    }

    /**
     * Retrieves the list of movies.
     *
     * @return The list of MovieDTO objects representing the movies.
     */
    @Override
    public List<MovieDTO> getMovies() {
        return moviesRepository.findAll()
                .stream()
                .map(moviesMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a movie with the specified ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return The MovieDTO object representing the retrieved movie.
     * @throws MovieNotFoundException If the movie with the specified ID is not found.
     */
    @Override
    public MovieDTO getMovie(int id) {
        final Map<AppConstant, Object> parameters = Map.of(AppConstant.MOVIE_ID, id);

        return moviesRepository.findById(id)
                .map(moviesMapper::toDto)
                .orElseThrow(() -> new MovieNotFoundException(parameters));
    }

    @Override
    public MovieDTO getMovieByName(String name) {
        final Map<AppConstant, Object> parameters = Map.of(
                AppConstant.MOVIE_NAME, name,
                AppConstant.MOVIE_ID, "None");

        throw new MovieNotFoundException(parameters);
    }

    @Override
    public void createMovie(MovieDTO movieDTO) {
        moviesRepository.save(moviesMapper.fromDto(movieDTO));
    }

    @Override
    public void deleteMovie(int id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public void updateMovie(int id, MovieDTO updated) {
        final MovieEntity movie = moviesMapper.fromDto(updated);
        movie.setId(id);
        moviesRepository.save(movie);
    }

    @Override
    public List<MovieDTO> getMoviesByReleaseYear(int year) {
        return moviesRepository.findAllByReleaseYear(year).stream()
                .map(moviesMapper::toDto)
                .toList();
    }
}
