package rs.ac.ni.pmf.rwa.movies.model;

import org.springframework.stereotype.Component;

@Component
public class MoviesMapper {

    public MovieDTO toDto(final MovieEntity movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .build();
    }

    public MovieEntity fromDto(final MovieDTO movieDTO) {
        return MovieEntity.builder()
                .id(movieDTO.getId())
                .name(movieDTO.getName())
                .genre(movieDTO.getGenre())
                .releaseYear(movieDTO.getReleaseYear())
                .description(movieDTO.getDescription())
                .build();
    }
}
