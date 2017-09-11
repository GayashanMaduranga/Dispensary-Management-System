package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "TestField", schema = "entitydb")
public class TestField {

    private SimpleIntegerProperty fId;
    private SimpleStringProperty fieldName;
    private SimpleStringProperty units;



    public TestField() {

        this.fId = new SimpleIntegerProperty();
        this.fieldName = new SimpleStringProperty();
        this.units = new SimpleStringProperty();

    }

    @Id
    @Column(name = "fID")
    @GeneratedValue
    public int getfId() {
        return fId.get();
    }

    public SimpleIntegerProperty fIdProperty() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId.set(fId);
    }

    @Column(name = "fieldName")
    public String getFieldName() {
        return fieldName.get();
    }

    public SimpleStringProperty fieldNameProperty() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName.set(fieldName);
    }

    @Column(name = "units")
    public String getUnits() {
        return units.get();
    }

    public SimpleStringProperty unitsProperty() {
        return units;
    }

    public void setUnits(String units) {
        this.units.set(units);
    }
}
