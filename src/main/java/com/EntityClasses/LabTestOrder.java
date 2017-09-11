package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "LabTestOrder", schema = "entitydb")
public class LabTestOrder {


    private SimpleIntegerProperty oId;
    private Date date;

    public LabTestOrder() {
        this.oId = new SimpleIntegerProperty();
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


}
