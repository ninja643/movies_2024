package rs.ac.ni.pmf.rwa.movies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class MovieNotFoundException extends RuntimeException {
    private final Map<AppConstant, Object> parameters;
}
