package rs.ac.ni.pmf.rwa.movies.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.movies.exception.ErrorDto;
import rs.ac.ni.pmf.rwa.movies.model.MovieDTO;

import java.util.List;

@Tag(name = "Movies Endpoints", description = "The set of endpoints for basic CRUD operations on movies")
@RequestMapping("/api/v1/movies")
public interface MoviesController {

    @Operation(
            operationId = "get-all-movies",
            summary = "Get the list of movies",
            description = "This is the endpoint for retrieving details of all movies under the given criteria")
    @GetMapping("")
    List<MovieDTO> getMovies();

    @Operation(
            operationId = "get-movie-by-id",
            summary = "Get the movie by ID",
            description = "Get the details of a specific movie using the movie ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - the movie was found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "MovieEntity not found. This happens if the non-existent id is given as a parameter",
                    content = @Content(
                            mediaType = "application-json",
                            contentSchema = @Schema(implementation = ErrorDto.class),
                            examples = @ExampleObject(
                                    name = "Movie1",
                                    value = "{\"id\": 1, \"title\": \"MovieEntity Title\"}"
                            )
                    )
            )
    })
    @GetMapping("/{movie_id}")
    MovieDTO getMovie(@PathVariable(name = "movie_id") int id);

    /*
        Method is for demonstration purposes only! It should not be the part of this API.
     */
    @GetMapping("/find/{name}")
    MovieDTO getMovieByName(final String name);

    @PostMapping("")
    void createMovie(@RequestBody MovieDTO movie);

    @DeleteMapping("/{movie_id}")
    void deleteMovie(@PathVariable("movie_id") int id);

    @PutMapping("/{movie_id}")
    void updateMovie(@PathVariable("movie_id") int id, @RequestBody MovieDTO updated);
}
