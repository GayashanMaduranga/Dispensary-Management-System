package com.EntityClasses;

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
@Table(name = "UtilityEquipment")
public class UtilityEquipment extends Equipment{


    private SimpleIntegerProperty lifetime;
    private SimpleStringProperty usableState;

    public UtilityEquipment() {

        this.lifetime = new SimpleIntegerProperty();
        this.usableState = new SimpleStringProperty();
    }


    @Column(name = "lifetime")
    public int getLifetime() {
        return lifetime.get();
    }

    public SimpleIntegerProperty lifetimeProperty() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime.set(lifetime);
    }

    @Column(name = "usableState")
    public String getUsableState() {
        return usableState.get();
    }

    public SimpleStringProperty usableStateProperty() {
        return usableState;
    }

    public void setUsableState(String usableState) {
        this.usableState.set(usableState);
    }
}
