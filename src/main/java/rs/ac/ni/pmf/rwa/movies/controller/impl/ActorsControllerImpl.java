package rs.ac.ni.pmf.rwa.movies.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.movies.controller.ActorsController;
import rs.ac.ni.pmf.rwa.movies.model.dto.ActorDTO;
import rs.ac.ni.pmf.rwa.movies.service.ActorsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ActorsControllerImpl implements ActorsController {


    private final ActorsService actorsService;


    @Override
    public List<ActorDTO> getAllActors() {
        return actorsService.getAll();
    }

    @Override
    public ActorDTO getActorById(int id) {
        return actorsService.getById(id);
    }

    @Override
    public void deleteActor(int id) {
        actorsService.delete(id);
    }

    @Override
    public void createActor(ActorDTO actor) {
        actorsService.create(actor);
    }

    @Override
    public void updateActor(ActorDTO actor, int id) {
        actorsService.update(actor,id);
    }
}
