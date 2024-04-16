package rs.ac.ni.pmf.rwa.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.ni.pmf.rwa.movies.exception.ErrorCode;
import rs.ac.ni.pmf.rwa.movies.exception.ErrorDto;
import rs.ac.ni.pmf.rwa.movies.exception.MovieNotFoundException;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class ErrorController {
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleMovieNotFound(final MovieNotFoundException e) {
        final Map<AppConstant, Object> parameters = e.getParameters();
        // TODO: This uses sets and the order is not always the same! Fix it!
        final String message = "MovieEntity with parameters ("
                + parameters.keySet().stream().map(Enum::name).collect(Collectors.joining(", "))
                + ") -> ("
                + parameters.values().stream().map(Objects::toString).collect(Collectors.joining(", "))
                + ") was not found";

        return ErrorDto.builder()
                .errorCode(ErrorCode.MOVIE_NOT_FOUND)
                .message(message)
                .parameters(parameters)
                .build();
    }
}
