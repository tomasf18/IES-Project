package sts.backend.core_app.models;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;

@Document(indexName = "logs")
public class LogEntity {
    @Id
    private String id;
    private String type; // "kafka" or "nginx"
    private String message;
    private long timestamp;

    public LogEntity() {
    }

    public LogEntity(String type, String message, long timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    
}