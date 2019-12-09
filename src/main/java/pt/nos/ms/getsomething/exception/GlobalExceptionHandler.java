package pt.nos.ms.getsomething.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pt.nos.ms.getsomething.dto.ErrorMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setCode(123);
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(ex.getClass() + " | " + ex.getLocalizedMessage() + " | " + ex.getMessage());
        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
