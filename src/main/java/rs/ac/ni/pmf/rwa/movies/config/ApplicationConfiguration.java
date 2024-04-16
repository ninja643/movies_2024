package rs.ac.ni.pmf.rwa.movies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesMapRepository;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public MoviesRepository getMapRepository() {
        return new MoviesMapRepository();
    }
}
