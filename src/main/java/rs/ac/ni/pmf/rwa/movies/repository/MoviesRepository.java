package rs.ac.ni.pmf.rwa.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.ni.pmf.rwa.movies.model.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findAllByReleaseYear(int year);
    List<MovieEntity> findAllByReleaseYearBetween(int startYear, int endYear);
    List<MovieEntity> findAllByReleaseYearBetweenAndGenre(int startYear, int endYear, Genre genre);
}
