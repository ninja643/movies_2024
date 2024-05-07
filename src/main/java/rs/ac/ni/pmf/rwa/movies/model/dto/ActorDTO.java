package rs.ac.ni.pmf.rwa.movies.model.dto;


import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;

@Builder
@Value
public class ActorDTO {
    int id;
    String name;
    String surname;
    Gender gender;
    String nationality;

}
