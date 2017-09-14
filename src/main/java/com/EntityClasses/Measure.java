package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Measure {

    private SimpleIntegerProperty measureId;
    private Date date;
    private SimpleIntegerProperty weight;
    private SimpleIntegerProperty height;
    private SimpleIntegerProperty temp;
    private SimpleIntegerProperty bp;
    private SimpleIntegerProperty respRate;
    private SimpleIntegerProperty pulseRate;
    private SimpleIntegerProperty bloodGlucose;




    public Measure() {
        this.measureId = new SimpleIntegerProperty();
        this.weight = new SimpleIntegerProperty();
        this.height = new SimpleIntegerProperty();
        this.temp = new SimpleIntegerProperty();
        this.bp = new SimpleIntegerProperty();
        this.respRate = new SimpleIntegerProperty();
        this.pulseRate = new SimpleIntegerProperty();
        this.bloodGlucose = new SimpleIntegerProperty();
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

    @Column(name = "weight")
    public int getWeight() {
        return weight.get();
    }

    public SimpleIntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }


    @Column(name = "height")
    public int getHeight() {
        return height.get();
    }

    public SimpleIntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    @Column(name = "temp")
    public int getTemp() {
        return temp.get();
    }

    public SimpleIntegerProperty tempProperty() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp.set(temp);
    }

    @Column(name = "bp")
    public int getBp() {
        return bp.get();
    }

    public SimpleIntegerProperty bpProperty() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp.set(bp);
    }

    @Column(name = "respRate")
    public int getRespRate() {
        return respRate.get();
    }

    public SimpleIntegerProperty respRateProperty() {
        return respRate;
    }

    public void setRespRate(int respRate) {
        this.respRate.set(respRate);
    }

    @Column(name = "pulseRate")
    public int getPulseRate() {
        return pulseRate.get();
    }

    public SimpleIntegerProperty pulseRateProperty() {
        return pulseRate;
    }

    public void setPulseRate(int pulseRate) {
        this.pulseRate.set(pulseRate);
    }

    @Column(name = "bloodGlucose")
    public int getBloodGlucose() {
        return bloodGlucose.get();
    }

    public SimpleIntegerProperty bloodGlucoseProperty() {
        return bloodGlucose;
    }

    public void setBloodGlucose(int bloodGlucose) {
        this.bloodGlucose.set(bloodGlucose);
    }

}
