package com.EntityClasses;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class MeasureValue {

    private SimpleIntegerProperty IID;
    private Date date;
    private SimpleIntegerProperty value;


    public MeasureValue() {

        this.IID = new SimpleIntegerProperty();
        this.value = new SimpleIntegerProperty();

    }

    @Id
    @Column(name = "IID")
    @GeneratedValue
    public int getIID() {
        return IID.get();
    }

    public SimpleIntegerProperty IIDProperty() {
        return IID;
    }

    public void setIID(int IID) {
        this.IID.set(IID);
    }

    //Date
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleStringProperty dateProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.date.toString());
        return dateString;
    }

    @Column(name = "value")
    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
//        SimpleStringProperty dosageString = new SimpleStringProperty();
//        dosageString.set(Integer.toString(this.getWeight())+" "+this.getDosageType());
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

}
