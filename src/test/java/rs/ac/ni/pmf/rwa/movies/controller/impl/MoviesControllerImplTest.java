package rs.ac.ni.pmf.rwa.movies.controller.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rs.ac.ni.pmf.rwa.movies.exception.MovieNotFoundException;
import rs.ac.ni.pmf.rwa.movies.model.dto.MovieDTO;
import rs.ac.ni.pmf.rwa.movies.model.entity.MovieEntity;
import rs.ac.ni.pmf.rwa.movies.model.mappers.MoviesMapper;
import rs.ac.ni.pmf.rwa.movies.repository.MoviesRepository;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoviesControllerImplTest {

    @Test
    public void testGetMovie_Success() {
        // Given
        final MovieDTO expectedMovie = MovieDTO.builder().build();

        final MoviesRepository moviesRepository = Mockito.mock(MoviesRepository.class);
        final MoviesMapper moviesMapper = Mockito.mock(MoviesMapper.class);
        final MoviesControllerImpl moviesController = new MoviesControllerImpl(moviesRepository, moviesMapper);

        final int id = 1;
        final MovieEntity movieEntity = MovieEntity.builder().build();
        Mockito.when(moviesRepository.findById(id)).thenReturn(Optional.of(movieEntity));
        Mockito.when(moviesMapper.toDto(movieEntity)).thenReturn(expectedMovie);

        // When
        final MovieDTO retrievedMovie = moviesController.getMovie(id);

        // Then
        assertThat(retrievedMovie).isEqualTo(expectedMovie);
    }
    
    @Test
    public void testGetMovie_ThrowMovieNotFoundException() {
        // Given
        final MoviesRepository moviesRepository = Mockito.mock(MoviesRepository.class);
        final MoviesMapper moviesMapper = Mockito.mock(MoviesMapper.class);
        final MoviesControllerImpl moviesController = new MoviesControllerImpl(moviesRepository, moviesMapper);

        final int id = 2;
        Mockito.when(moviesRepository.findById(id)).thenReturn(Optional.empty());    
    
        // When & Then
        final Map<AppConstant, Object> parameters = Map.of(AppConstant.MOVIE_ID, id);

        assertThatThrownBy(() -> moviesController.getMovie(id))
          .isInstanceOf(MovieNotFoundException.class);
    }
}
