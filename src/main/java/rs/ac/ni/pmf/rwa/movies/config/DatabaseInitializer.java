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
            MovieEntity.builder().name("711 Ocean Drive").releaseYear(1999).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Abbott and Costello in the Foreign Legion").releaseYear(1999).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("Across the Badlands").releaseYear(2010).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("The Admiral Was a Lady").releaseYear(2022).genre(Genre.COMEDY).build(),
            MovieEntity.builder().name("All About Eve").releaseYear(1999).genre(Genre.COMEDY).build()
    );

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Filling database with initial data");
        moviesRepository.saveAll(movies);

        log.info("Movies in database: {}", moviesRepository.count());
    }
}
