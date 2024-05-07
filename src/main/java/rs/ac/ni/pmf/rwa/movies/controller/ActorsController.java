package rs.ac.ni.pmf.rwa.movies.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.movies.model.dto.ActorDTO;

import java.util.List;

@RequestMapping("api/v1/actors")
public interface ActorsController {

    @GetMapping("")
    List<ActorDTO> getAllActors();

    @GetMapping("/{id}")
    ActorDTO getActorById(@PathVariable("id") int id);

    @DeleteMapping("/{actors_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteActor(@PathVariable ("actors_id") int id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void createActor(@RequestBody ActorDTO actor);

    @PutMapping("/{actors_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateActor(@RequestBody ActorDTO actor, @PathVariable("actors_id") int id);



}
