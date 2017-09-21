package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Equipment")
public class Equipment extends RecursiveTreeObject<Equipment> {


    private SimpleIntegerProperty equipmentID;
    private SimpleIntegerProperty stock;
    private SimpleStringProperty equipmentName;

    public Equipment() {

        this.equipmentID = new SimpleIntegerProperty();
        this.stock = new SimpleIntegerProperty();
        this.equipmentName = new SimpleStringProperty();
    }

    @Id
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

    @Column(name = "stock")
    public int getStock() {
        return stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    @Column(name = "equipmentName")
    public String getEquipmentName() {
        return equipmentName.get();
    }

    public SimpleStringProperty equipmentNameProperty() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName.set(equipmentName);
    }
}
