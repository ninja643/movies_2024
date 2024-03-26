package rs.ac.ni.pmf.rwa.movies.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.movies.controller.MoviesController;
import rs.ac.ni.pmf.rwa.movies.model.Movie;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.model.MoviesMapper;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesListRepository;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;

import java.util.List;

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
        return moviesRepository.findById(id)
                .map(moviesMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id: " + id + " does not exist"));
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
