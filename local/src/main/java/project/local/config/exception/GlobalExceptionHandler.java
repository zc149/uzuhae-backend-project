package project.local.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> ioExceoptionHandler(final IOException e) {

        log.error("마이데이터 오류", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("마이데이터를 불러오지 못했습니다." + e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundExceptionHandler(final EntityNotFoundException e) {

        log.error("EntityNotFound", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("해당 값을 불러오지 못했습니다." + e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(final NullPointerException e) {

        log.error("NullPointer", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("요청한 값을 찾을 수 없습니다." + e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceoptionHandler(final NoSuchElementException e) {

        log.error("NoSuchElement", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("요청한 값이 없습니다." + e.getMessage());
    }
}
