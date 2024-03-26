package rs.ac.ni.pmf.rwa.movies.model;

import org.springframework.stereotype.Component;

@Component
public class MoviesMapper {

    public MovieDTO toDto(final Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .build();
    }

    public Movie fromDto(final MovieDTO movieDTO) {
        return Movie.builder()
                .id(movieDTO.getId())
                .name(movieDTO.getName())
                .genre(movieDTO.getGenre())
                .releaseYear(movieDTO.getReleaseYear())
                .description(movieDTO.getDescription())
                .build();
    }
}
