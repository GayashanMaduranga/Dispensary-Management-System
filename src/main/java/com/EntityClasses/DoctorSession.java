package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class DoctorSession {

    private SimpleIntegerProperty sessionID;
    private Date date;
    private SimpleStringProperty complaint;



    public DoctorSession() {

        this.sessionID = new SimpleIntegerProperty();
        this.complaint = new SimpleStringProperty();

    }

    @Id
    @Column(name = "sessionID")
    @GeneratedValue
    public int getSessionID() {
        return sessionID.get();
    }

    public SimpleIntegerProperty sessionIDProperty() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID.set(sessionID);
    }

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


    @Column(name = "complaint")
    public String getName() {
        return complaint.get();
    }

    public SimpleStringProperty nameProperty() {
        return complaint;
    }

    public void setName(String name) {
        this.complaint.set(name);
    }

}
