package rs.ac.ni.pmf.rwa.movies.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.movies.model.entity.ActorEntity;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.repository.ActorsRepository;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private final MoviesRepository moviesRepository;
    private final ActorsRepository actorsRepository;

    private static final List<MovieEntity> movies = List.of(
            MovieEntity.builder().name("711 Ocean Drive").releaseYear(1999).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Abbott and Costello in the Foreign Legion").releaseYear(1999).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Across the Badlands").releaseYear(2010).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("The Admiral Was a Lady").releaseYear(2022).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("All About Eve").releaseYear(1999).genre(Genre.COMEDY).build()
    );

    private static final List<ActorEntity> actors = List.of(
            ActorEntity.builder().name("Edmond").surname("O'Brien").gender(Gender.MALE).nationality("American").build(),
            ActorEntity.builder().name("Otto").surname("O'Brien").gender(Gender.MALE).nationality("American").build()// glumio u 711 Ocean Drive, The Admiral Was a Lady
    );

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Filling database with initial data");
        moviesRepository.saveAll(movies);
        actorsRepository.saveAll(actors);

        log.info("Movies in database: {}", moviesRepository.count());
        log.info("Actors in database: {}", actorsRepository.count());
    }
}
