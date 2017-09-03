package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Prescription", schema = "entitydb")
public class Prescription {


    private SimpleIntegerProperty presId;
    private Date date;


    public Prescription() {
        this.presId = new SimpleIntegerProperty();
    }


    //Date
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "presID")
    @GeneratedValue
    public int getPresId() {
        return presId.get();
    }

    public void setPresId(int presId) {
        this.presId.set(presId);
    }

    public SimpleIntegerProperty presIdProperty() {
        return presId;
    }

}
