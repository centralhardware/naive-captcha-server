package ru.centralhardware.testTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class IOExceptionHandler {

    @ExceptionHandler(value = {
            IOException.class
    })
    protected ResponseEntity<?> handle(IOException exception, WebRequest request){
        log.warn("unable to read captcha image file for request " +
                        request.getHeader(RequestIdInterceptor.REQUEST_ID_HEADER_NAME), exception);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                build();
    }

}
