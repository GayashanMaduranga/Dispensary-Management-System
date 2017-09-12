package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "LabTestOrder")
public class LabTestOrder {


    private SimpleIntegerProperty oId;
    private Date date;

    //Uni directional one to many relationship
    @OneToMany(cascade = CascadeType.ALL)
    List<Sample> samples;


    //Bi directional one to many Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    MainTest mainTest;

    public LabTestOrder() {
        this.oId = new SimpleIntegerProperty();
        samples = new ArrayList<>();
    }

    @Id
    @Column(name = "oID")
    @GeneratedValue
    public int getoId() {
        return oId.get();
    }

    public void setoId(int oId) {
        this.oId.set(oId);
    }

    public SimpleIntegerProperty oIdProperty() {
        return oId;
    }

    //Date
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }
}
