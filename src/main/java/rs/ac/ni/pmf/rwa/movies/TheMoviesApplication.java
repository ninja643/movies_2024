package rs.ac.ni.pmf.rwa.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.service.ActorsService;

@SpringBootApplication
public class TheMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheMoviesApplication.class, args);

	}
}
