package com.gamaza.examples.springjpacriteria.config;

import com.gamaza.examples.springjpacriteria.dto.ProblemDetailDto;
import com.gamaza.examples.springjpacriteria.exception.NotFoundException;
import com.gamaza.examples.springjpacriteria.util.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Global Exception Handler for REST Controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Global constants
    private static final String DESCRIPTION = "description";

    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<ProblemDetailDto> handleNotFoundException(Exception exception) {
        ProblemDetailDto result = ProblemDetailDto.ProblemDetailDtoBuilder
                .newInstance(NOT_FOUND, ExceptionUtils.getCauseOrDefaultMessage(exception))
                .withTitle("Resource not found")
                .withProperty(DESCRIPTION, "The resource can not be found on the server")
                .build();

        return new ResponseEntity<>(result, NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ProblemDetailDto> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body(
                ProblemDetailDto.ProblemDetailDtoBuilder
                        .newInstance(INTERNAL_SERVER_ERROR, ExceptionUtils.getCauseOrDefaultMessage(exception))
                        .withTitle("Internal server error")
                        .withProperty(DESCRIPTION, "Server response with an Internal Error")
                        .build()
        );
    }

}
