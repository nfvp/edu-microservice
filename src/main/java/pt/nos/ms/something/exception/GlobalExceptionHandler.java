package pt.nos.ms.something.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pt.nos.ms.something.dto.ErrorMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    Logger logger = Logger.getGlobal();
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> genericExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setCode(100);
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(ex.getClass() + " | " + ex.getLocalizedMessage() + " | " + ex.getMessage());
        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorMessageDTO> ioExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setCode(101);
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(ex.getClass() + " | " + ex.getLocalizedMessage() + " | " + ex.getMessage());
        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.PAYLOAD_TOO_LARGE);
    }
    
    @ExceptionHandler(SomethingNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> notFoundExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setCode(404);
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(ex.getMessage());
        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
