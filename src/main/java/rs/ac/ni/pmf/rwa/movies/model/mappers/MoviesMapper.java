package rs.ac.ni.pmf.rwa.movies.model.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.movies.model.dto.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieActorEntity;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MoviesMapper {

    /**
     * Converts a MovieEntity object to a MovieDTO object.
     *
     * @param movie The MovieEntity object to be converted.
     * @return The converted MovieDTO object.
     */
    public MovieDTO toDto(final MovieEntity movie) {

        final Map<String, Double> salaries = new HashMap<>();

        List<MovieActorEntity> movieActorEntityList = movie.getActors();
        for (MovieActorEntity movieActorEntity : movieActorEntityList) {
            salaries.put(movieActorEntity.getActor().getName(), movieActorEntity.getSalary());
        }

        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .actorsSalaries(salaries)
                .build();
    }

    /**
     * Converts a MovieDTO object to a MovieEntity object.
     *
     * @param movieDTO The MovieDTO object to be converted.
     * @return The converted MovieEntity object.
     */
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
