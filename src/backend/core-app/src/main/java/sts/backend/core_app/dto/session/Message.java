package sts.backend.core_app.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    // Message is an array of records
    @JsonProperty("records")
    private Record[] records;

    public Message() {
    }

    public Message(Record[] records) {
        this.records = records;
    }

    public Record[] getRecords() {
        return records;
    }

    public void setRecords(Record[] records) {
        this.records = records;
    }

    @Override
    public String toString() {
        StringBuilder recordsString = new StringBuilder();
        for (Record record : records) {
            recordsString.append(record.toString()).append("\n");
        }

        return "{" +
                "records: { " + recordsString +
                "} }";
    }

}
