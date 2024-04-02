package rs.ac.ni.pmf.rwa.movies.exception;

import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.rwa.movies.shared.AppConstant;

import java.util.Map;

@Value
@Builder
public class ErrorDto {
    ErrorCode errorCode;
    String message;
    Map<AppConstant, Object> parameters;
}
