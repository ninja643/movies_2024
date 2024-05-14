package rs.ac.ni.pmf.rwa.movies;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.ni.pmf.rwa.movies.config.DatabaseInitializer;
import rs.ac.ni.pmf.rwa.movies.model.entity.ActorEntity;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.repository.ActorsRepository;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;

import java.util.List;

 @DataJpaTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TheMoviesApplicationTests {


	private final ActorsRepository actorsRepository;

	private final MoviesRepository moviesRepository;
  @Autowired
	 public TheMoviesApplicationTests(ActorsRepository actorsRepository, MoviesRepository moviesRepository) {
		 this.actorsRepository = actorsRepository;
		 this.moviesRepository = moviesRepository;
	 }

	 @Test
//	@Transactional
	void shouldCheckDatabase() {

//		System.out.println("-------------------------");
		List<ActorEntity> list = actorsRepository.findAll();
		for (ActorEntity actorEntity : list) {
			Assertions.assertThat(actorEntity.getLeadingRoles().size()).isEqualTo(1);
//			System.out.println("****** Actor name:" + actorEntity.getName());
//			for (MovieEntity movieEntity : actorEntity.getLeadingRoles() ) {
//				System.out.println("----" +movieEntity.getName());
//			}
		}
//		System.out.println("--------------------------");

		}
	}


