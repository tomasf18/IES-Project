package sts.backend.core_app.dto.admin;

import java.time.LocalDate;

public class SensorsLast5Days {
    private LocalDate date;
    private int value;

    public SensorsLast5Days() {
    }

    public SensorsLast5Days(LocalDate date, int value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
