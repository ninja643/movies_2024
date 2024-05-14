package rs.ac.ni.pmf.rwa.movies.model.dto;

import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.HashMap;
import java.util.Map;

@Value
@Builder
public class MovieDTO {
    int id;
    String name;
    String description;
    int releaseYear;
    Genre genre;

    @Builder.Default
    Map<String, Double> actorsSalaries = new HashMap<>();
}
