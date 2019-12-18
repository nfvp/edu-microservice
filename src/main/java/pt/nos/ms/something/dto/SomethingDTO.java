package pt.nos.ms.something.dto;

import java.time.LocalDateTime;

public class SomethingDTO {
    
    private long id;
    
    private String fullName;
    
    private LocalDateTime timestamp;
    
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
}
