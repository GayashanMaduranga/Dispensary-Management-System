package com.EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Embeddable;

@Embeddable
public class EmploymentDetails {

    private StringProperty field;
    private StringProperty details;

    public EmploymentDetails() {
        field = new SimpleStringProperty();
        details = new SimpleStringProperty();
    }

    public String getField() {
        return field.get();
    }

    public StringProperty fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }

    public String getDetails() {
        return details.get();
    }

    public StringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }
}
