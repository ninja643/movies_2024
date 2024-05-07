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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private final MoviesRepository moviesRepository;
    private final ActorsRepository actorsRepository;

    public static final MovieEntity MOVIE_1 = MovieEntity.builder()
            .name("711 Ocean Drive")
            .releaseYear(1999)
            .genre(Genre.COMEDY)
            .build();
    public static final MovieEntity MOVIE_2 = MovieEntity.builder()
            .name("Abbott and Costello in the Foreign Legion")
            .releaseYear(1999)
            .genre(Genre.COMEDY)
            .build();
    private static final List<MovieEntity> movies = List.of(
            MOVIE_1,
            MOVIE_2,
            MovieEntity.builder().name("Across the Badlands").releaseYear(2010).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("The Admiral Was a Lady").releaseYear(2022).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("All About Eve").releaseYear(1999).genre(Genre.COMEDY).build()
    );

    public static final ActorEntity ACTOR_2 = ActorEntity.builder()
            .name("Otto")
            .surname("O'Brien")
            .gender(Gender.MALE
            ).nationality("American")
            .movies(new ArrayList<>()).build();
    private static final List<ActorEntity> actors = List.of(
            ActorEntity.builder().name("Edmond").surname("O'Brien").gender(Gender.MALE).nationality("American").build(),
            ACTOR_2// glumio u 711 Ocean Drive, The Admiral Was a Lady
    );

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Filling database with initial data");

//        actorsRepository.saveAll(actors);
//        moviesRepository.saveAll(movies);

//        moviesRepository.save(MOVIE_1);
//        moviesRepository.save(MOVIE_2);
        MOVIE_1.setActor(ACTOR_2);
        MOVIE_2.setActor(ACTOR_2);

        ACTOR_2.getMovies().add(MOVIE_1);
        ACTOR_2.getMovies().add(MOVIE_2);
        actorsRepository.save(ACTOR_2);

//        log.info("Movies in database: {}", moviesRepository.count());
//        log.info("Actors in database: {}", actorsRepository.count());
    }
}
