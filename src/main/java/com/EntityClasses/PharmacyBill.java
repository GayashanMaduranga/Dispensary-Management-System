package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "PharmacyBill", schema = "entitydb")
public class PharmacyBill {


    private SimpleIntegerProperty bID;
    private Date date;
    private SimpleDoubleProperty total;

    public PharmacyBill() {
        this.bID = new SimpleIntegerProperty();
        this.total = new SimpleDoubleProperty();
    }


    //Date


    @Id
    @Column(name = "presID")
    @GeneratedValue
    public int getbID() {
        return bID.get();
    }

    public SimpleIntegerProperty bIDProperty() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID.set(bID);
    }


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "total")
    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
