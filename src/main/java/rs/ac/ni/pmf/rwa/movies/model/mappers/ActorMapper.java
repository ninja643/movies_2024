package rs.ac.ni.pmf.rwa.movies.model.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.movies.model.dto.ActorDTO;
import rs.ac.ni.pmf.rwa.movies.model.entity.ActorEntity;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;

import java.util.stream.Collectors;


@Component
public class ActorMapper {

    public ActorDTO toDto(final ActorEntity actorEntity){
        return ActorDTO.builder()
                .id(actorEntity.getId())
                .name(actorEntity.getName())
                .surname(actorEntity.getSurname())
                .gender(actorEntity.getGender())
                .nationality(actorEntity.getNationality())
//                .movies(actorEntity.getMovies() != null
//                        ? actorEntity.getMovies().stream().map(MovieEntity::getName).collect(Collectors.joining(", "))
//                        : null)
                .build();
    }

    public ActorEntity fromDTO(final ActorDTO actorDTO){
        return ActorEntity.builder()
                .id(actorDTO.getId())
                .name(actorDTO.getName())
                .surname(actorDTO.getSurname())
                .gender(actorDTO.getGender())
                .nationality(actorDTO.getNationality())
                .build();
    }
}
