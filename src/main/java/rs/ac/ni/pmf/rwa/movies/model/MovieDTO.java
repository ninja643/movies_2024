package rs.ac.ni.pmf.rwa.movies.model;

import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

@Value
@Builder
public class MovieDTO {
    int id;
    String name;
    String description;
    int releaseYear;
    Genre genre;
}
