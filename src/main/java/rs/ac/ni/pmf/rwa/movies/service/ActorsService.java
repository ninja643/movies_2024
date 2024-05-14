package rs.ac.ni.pmf.rwa.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.rwa.movies.exception.ActorNotFoundException;
import rs.ac.ni.pmf.rwa.movies.model.dto.ActorDTO;
import rs.ac.ni.pmf.rwa.movies.model.entity.ActorEntity;
import rs.ac.ni.pmf.rwa.movies.model.mappers.ActorMapper;
import rs.ac.ni.pmf.rwa.movies.repository.ActorsRepository;

import java.util.List;

@Service
public class ActorsService {

    private final ActorsRepository actorsRepository;

    private final ActorMapper mapper;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository, ActorMapper mapper) {
        this.actorsRepository = actorsRepository;
        this.mapper = mapper;
    }

    public List<ActorDTO> getAll(){
        final List<ActorEntity> actors = actorsRepository.findAll();
        return  actors.stream().map(mapper::toDto).toList();
    }


    public ActorDTO getById(int id){
        return actorsRepository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(ActorNotFoundException::new);
    }

    public void delete(int id){
       actorsRepository.deleteById(id);
    }

    public void create(ActorDTO actor){
        ActorEntity actorEntity = mapper.fromDTO(actor);
        actorsRepository.save(actorEntity);
    }

    public void update(ActorDTO actor, int id){
    final ActorEntity actorEntity = mapper.fromDTO(actor);
    actorEntity.setId(id);
    actorsRepository.save(actorEntity);
    }



}
