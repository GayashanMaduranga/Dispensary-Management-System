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
@DiscriminatorValue("Equipment")
public class Equipment extends Item {

    private SimpleStringProperty equipmentName;

    public Equipment() {

        this.equipmentName = new SimpleStringProperty();
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
