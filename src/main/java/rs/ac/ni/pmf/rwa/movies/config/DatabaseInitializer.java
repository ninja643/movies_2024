package rs.ac.ni.pmf.rwa.movies.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.movies.model.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private final MoviesRepository moviesRepository;

    private static final List<MovieEntity> movies = List.of(
            MovieEntity.builder().name("711 Ocean Drive").genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Abbott and Costello in the Foreign Legion").genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Across the Badlands").genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("The Admiral Was a Lady").genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("All About Eve").genre(Genre.COMEDY).build()
    );

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Filling database with initial data");
        moviesRepository.saveAll(movies);

        log.info("Movies in database: {}", moviesRepository.count());
    }
}
