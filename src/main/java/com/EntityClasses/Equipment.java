package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
@Table(name = "Equipment")
public class Equipment {


    private SimpleIntegerProperty equipmentID;
    private Date purchaseDate;
    private SimpleDoubleProperty cost;

    public Equipment() {

        this.equipmentID = new SimpleIntegerProperty();
        this.cost = new SimpleDoubleProperty();
    }

    @Id
    @Column(name = "equipmentID")
    @GeneratedValue
    public int getEquipmentID() {
        return equipmentID.get();
    }

    public SimpleIntegerProperty equipmentIDProperty() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID.set(equipmentID);
    }

    @Column(name = "purchaseDate")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(name = "cost")
    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }
}
