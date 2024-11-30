package sts.backend.core_app.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;

@Document(indexName = "sensorslogentity")
public class SensorsLogEntity {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private Long teamId;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Long)
    private Long timestamp;

    public SensorsLogEntity() {
    }

    public SensorsLogEntity(String id, Long teamId, String message, Long timestamp) {
        this.id = id;
        this.teamId = teamId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    
}
