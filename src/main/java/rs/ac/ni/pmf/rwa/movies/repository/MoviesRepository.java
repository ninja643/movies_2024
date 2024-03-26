package rs.ac.ni.pmf.rwa.movies.repository;

import rs.ac.ni.pmf.rwa.movies.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository {
    List<Movie> findAll();
    Optional<Movie> findById(int id);

    Movie save(Movie movie);

    void deleteById(int id);
}
