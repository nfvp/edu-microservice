package pt.nos.ms.getsomething.dto;

import java.time.LocalDateTime;

public class ErrorMessageDTO {
    
    private int code;
    private LocalDateTime timestamp;
    private String description;
    
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

}