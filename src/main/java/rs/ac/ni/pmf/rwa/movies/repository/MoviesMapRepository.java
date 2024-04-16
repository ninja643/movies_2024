package rs.ac.ni.pmf.rwa.movies.repository;

import rs.ac.ni.pmf.rwa.movies.model.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MoviesMapRepository implements MoviesRepository {

    private static int lastId;

    private static final Map<Integer, MovieEntity> movies = new HashMap<>();

    static {
        movies.put(1,
                MovieEntity.builder()
                        .id(1)
                        .name("The Gentlemen")
                        .genre(Genre.ACTION)
                        .releaseYear(2024)
                        .build());

        movies.put(2,
                MovieEntity.builder()
                        .id(2)
                        .name("The Sting")
                        .genre(Genre.ACTION)
                        .releaseYear(1972)
                        .build());

        lastId = 2;
    }

    @Override
    public List<MovieEntity> findAll() {
        return movies.values().stream().toList();
    }

    @Override
    public Optional<MovieEntity> findById(int id) {
        return Optional.ofNullable(movies.get(id));
    }

    @Override
    public MovieEntity save(MovieEntity movie) {
        return movies.put(movie.getId(), movie);
    }

    @Override
    public void deleteById(int id) {
        movies.remove(id);
    }

    @Override
    public void identifyBean() {
        System.out.println("Using the Map Repo bean");
    }
}
