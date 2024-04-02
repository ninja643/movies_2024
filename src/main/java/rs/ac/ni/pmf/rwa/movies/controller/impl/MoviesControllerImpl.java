package rs.ac.ni.pmf.rwa.movies.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.movies.controller.MoviesController;
import rs.ac.ni.pmf.rwa.movies.exception.MovieNotFoundException;
import rs.ac.ni.pmf.rwa.movies.model.Movie;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.model.MoviesMapper;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesListRepository;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MoviesControllerImpl implements MoviesController {

    private final MoviesRepository moviesRepository = new MoviesListRepository();
    private final MoviesMapper moviesMapper;

    @Override
    public List<MovieDTO> getMovies() {
        return moviesRepository.findAll()
                .stream()
                .map(moviesMapper::toDto)
                .toList();
    }

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
//                new EnumMap<>(AppConstant.class);
//        parameters.put(AppConstant.MOVIE_NAME, name);
//        parameters.put(AppConstant.MOVIE_ID, "None");

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
        final Movie movie = moviesMapper.fromDto(updated);
        movie.setId(id);
        moviesRepository.save(movie);
    }
}
