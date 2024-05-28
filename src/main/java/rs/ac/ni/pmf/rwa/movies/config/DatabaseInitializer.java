package rs.ac.ni.pmf.rwa.movies.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.movies.model.entity.*;
import rs.ac.ni.pmf.rwa.movies.repository.*;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private final MoviesRepository moviesRepository;
    private final ActorsRepository actorsRepository;
    private final BudgetRepository budgetRepository;

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

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


    public static final ActorEntity ACTOR_1 = ActorEntity.builder()
            .name("Edmond")
            .surname("O'Brien")
            .gender(Gender.MALE)
            .nationality("American")
            .build();
    public static final ActorEntity ACTOR_2 = ActorEntity.builder()
            .name("Otto")
            .surname("O'Brien")
            .gender(Gender.MALE)
            .nationality("American")
            .movies(new ArrayList<>())
            .leadingRoles(new ArrayList<>()).build();

    private static final List<ActorEntity> actors = List.of(
            ACTOR_1,
            ACTOR_2// glumio u 711 Ocean Drive, The Admiral Was a Lady
    );

   public static final BudgetEntity BUDGET1 = BudgetEntity.builder()
           .budget(200000d)
           .build();

    public static final BudgetEntity BUDGET2 = BudgetEntity.builder()
            .budget(10000.25)
            .build();


    @Override
    // @Transactional
    public void afterPropertiesSet() throws Exception {
        log.info("Adding initial users");
        final UserEntity admin = UserEntity.builder()
            .username("admin")
            .enabled(true)
            .email("admin@mail.com")
            .password(passwordEncoder.encode("admin"))
            .locked(false)
            .name("The Admin")
            .roles(Set.of(UserRole.ADMIN, UserRole.USER))
            .build();

        usersRepository.save(admin);

        log.info("Filling database with initial data");

//        actorsRepository.saveAll(actors);
//        moviesRepository.saveAll(movies);

//        moviesRepository.save(MOVIE_1);
//        moviesRepository.save(MOVIE_2);




//        MOVIE_1.setActor(ACTOR_2);
//        MOVIE_2.setActor(ACTOR_2);

//        ACTOR_2.getMovies().add(MOVIE_1);
//        ACTOR_2.getMovies().add(MOVIE_2);

        ACTOR_1.addMainRoles(MOVIE_1);
        ACTOR_2.addMainRoles(MOVIE_2);

        actorsRepository.save(ACTOR_1);
        actorsRepository.save(ACTOR_2);

        budgetRepository.save(BUDGET1);
        budgetRepository.save(BUDGET2);

        MOVIE_1.setMovieBudget(BUDGET1);
        MOVIE_2.setMovieBudget(BUDGET2);

        MOVIE_1.setLeadingRole(ACTOR_1);
        MOVIE_2.setLeadingRole(ACTOR_2);


        MovieActorEntity movieActorEntity1 = new MovieActorEntity();
        MovieActorEntity.MovieActorPK movieActorPK1 = new MovieActorEntity.MovieActorPK();
        movieActorPK1.setMovieId(MOVIE_1.getId());
        movieActorPK1.setActorId(ACTOR_1.getId());

        movieActorEntity1.setId(movieActorPK1);
        movieActorEntity1.setMovie(MOVIE_1);
        movieActorEntity1.setActor(ACTOR_1);
        movieActorEntity1.setSalary(1_000_000.0);
        MOVIE_1.getActors().add(movieActorEntity1);

        MovieActorEntity movieActorEntity2 = new MovieActorEntity();
        MovieActorEntity.MovieActorPK movieActorPK2 = new MovieActorEntity.MovieActorPK();
        movieActorPK2.setMovieId(MOVIE_1.getId());
        movieActorPK2.setActorId(ACTOR_2.getId());

        movieActorEntity2.setId(movieActorPK2);
        movieActorEntity2.setMovie(MOVIE_1);
        movieActorEntity2.setActor(ACTOR_2);
        movieActorEntity2.setSalary(700_000.0);
        MOVIE_1.getActors().add(movieActorEntity2);


        moviesRepository.save(MOVIE_1);
        moviesRepository.save(MOVIE_2);

//        log.info("Movies in database: {}", moviesRepository.count());
//        log.info("Actors in database: {}", actorsRepository.count());

    }

}

