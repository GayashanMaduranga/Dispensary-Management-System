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
@Table(name = "Sample")
public class Sample {

    private SimpleIntegerProperty sId;
    private SimpleStringProperty type;
    private Date Date;



    public Sample() {

        this.sId = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();

    }

    @Id
//    @Column(name = "SID")
    @GeneratedValue
    public int getsId() {
        return sId.get();
    }

    public void setsId(int sId) {
        this.sId.set(sId);
    }

    public SimpleIntegerProperty sIdProperty() {
        return sId;
    }

    //type
//    @Column(name = "type")
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }


    //date
//    @Column(name = "date")
    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }


}
