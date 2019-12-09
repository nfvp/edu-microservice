package pt.nos.ms.getsomething.dao;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "T_SOMETHING")
public class SomethingDAO {
    
    @Id
    @GeneratedValue
    @Column(name = "SMT_ID", length = 10)
    private long id;
    
    @Column(name = "SMT_ONE_NAME", length = 150)
    private String oneName;
    
    @Column(name = "SMT_ANOTHER_NAME", length = 150)
    private String anotherName;
    
    @Column(name = "SMT_TIMESTAMP", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;
    
    @Column(name = "SMT_CREATED_AT")
    private LocalDateTime createdAt;
    
    @Column(name = "SMT_CREATED_BY", length = 50)
    private String createdBy;
    
    @Column(name = "SMT_UPDATED_AT")
    private LocalDateTime updatedAt;
    
    @Column(name = "SMT_UPDATED_BY", length = 50)
    private String updatedBy;
    
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setOneName(String oneName) {
        this.oneName = oneName;
    }
    
    public String getOneName() {
        return oneName;
    }
    
    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }
    
    public String getAnotherName() {
        return anotherName;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
}
