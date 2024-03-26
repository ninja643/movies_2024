package rs.ac.ni.pmf.rwa.movies.model;

import lombok.Builder;
import lombok.Data;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

@Data
@Builder
public class Movie {
    int id;
    String name;
    String description;
    int releaseYear;
    Genre genre;
}
