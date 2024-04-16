package rs.ac.ni.pmf.rwa.movies.repository;

import rs.ac.ni.pmf.rwa.movies.model.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository {
    List<MovieEntity> findAll();
    Optional<MovieEntity> findById(int id);

    MovieEntity save(MovieEntity movie);

    void deleteById(int id);

    void identifyBean();
}
