package rs.ac.ni.pmf.rwa.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;

import java.util.List;

public interface MoviesRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findAllByReleaseYear(int year);
}
