package cl.utem.inf.backend.controllers;

import cl.utem.inf.backend.domains.ErrorVO;
import cl.utem.inf.backend.exceptions.BadCredentialException;
import cl.utem.inf.backend.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author jpbb
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     *
     * @param e Excepción inesperada
     * @return Un error 401 con la excepción generada
     */
    @ExceptionHandler({BadCredentialException.class})
    public ResponseEntity<ErrorVO> handleException(BadCredentialException e) {
        LOGGER.error("Credenciales Malas: {}", e.getLocalizedMessage());
        LOGGER.debug("Credenciales Malas: {}", e.getMessage(), e);

        final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        final ErrorVO error = new ErrorVO(e.getLocalizedMessage());

        return new ResponseEntity<>(error, httpStatus);
    }

    /**
     *
     * @param e Excepción inesperada
     * @return Un error 404 con la excepción generada
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorVO> handleException(NotFoundException e) {
        LOGGER.error("No encontrado: {}", e.getLocalizedMessage());
        LOGGER.debug("No encontrado: {}", e.getMessage(), e);

        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final ErrorVO error = new ErrorVO(e.getLocalizedMessage());

        return new ResponseEntity<>(error, httpStatus);
    }

    /**
     *
     * @param e Excepción inesperada
     * @return Un error 500 con la excepción generada
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorVO> handleException(Exception e) {
        LOGGER.error("Error no manejado: {}", e.getLocalizedMessage());
        LOGGER.debug("Error no manejado: {}", e.getMessage(), e);

        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorVO error = new ErrorVO(e.getLocalizedMessage());

        return new ResponseEntity<>(error, httpStatus);
    }

}
