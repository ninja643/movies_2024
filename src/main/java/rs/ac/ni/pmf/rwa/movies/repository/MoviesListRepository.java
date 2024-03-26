package rs.ac.ni.pmf.rwa.movies.repository;

import rs.ac.ni.pmf.rwa.movies.model.Movie;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoviesListRepository implements MoviesRepository {

    private static int lastId;

    private static final List<Movie> movies = new ArrayList<>();

    static {
        movies.add(Movie.builder()
                .id(1)
                .name("The Gentlemen")
                .genre(Genre.ACTION)
                .releaseYear(2024)
                .build());

        movies.add(Movie.builder()
                .id(2)
                .name("The Sting")
                .genre(Genre.ACTION)
                .releaseYear(1972)
                .build());

        lastId = 2;
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public Optional<Movie> findById(int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst();
    }

    @Override
    public Movie save(Movie movie) {
        final Optional<Movie> optionalMovie = movies.stream()
                .filter(m -> m.getId() == movie.getId())
                .findFirst();

        if (optionalMovie.isPresent()) {
            final Movie existingMovie = optionalMovie.get();
            movies.remove(existingMovie);
            movies.add(movie);
        } else {
            // add a new movie
            movie.setId(++lastId);
            movies.add(movie);
        }

        return movie;
    }

    @Override
    public void deleteById(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }
}
