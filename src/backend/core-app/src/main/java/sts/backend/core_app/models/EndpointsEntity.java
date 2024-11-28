package sts.backend.core_app.models;

import org.springframework.data.elasticsearch.annotations.Document;
// import org.springframework.data.elasticsearch.annotations.Field;
// import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;


@Document(indexName = "endpointslogentity")
public class EndpointsEntity {
    // TODO: integrate with nginx logs!!
        
    @Id
    private String id;

    public EndpointsEntity() {
    }

    public EndpointsEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
