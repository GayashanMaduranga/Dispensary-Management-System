package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Measure {

    private SimpleIntegerProperty measureId;
    private Date dateUpdated;
    private SimpleStringProperty name;

    private List<MeasureValue> values;


    public Measure() {

        this.measureId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();

    }

    @Id
    @Column(name = "measureId")
    @GeneratedValue
    public int getMeasureId() {
        return measureId.get();
    }

    public SimpleIntegerProperty measureIdProperty() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId.set(measureId);
    }

    @Column(name = "dateUpdated")
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public SimpleStringProperty dateUpdatedProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.dateUpdated.toString());
        return dateString;
    }


    @Column(name = "name")
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<MeasureValue> getValues() {
        return values;
    }

    public void setValues(List<MeasureValue> values) {
        this.values = values;
    }
}
