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
public class Allergy {

    private SimpleIntegerProperty aID;
    private SimpleStringProperty description;
    private SimpleStringProperty cause;


    public Allergy() {

        this.aID = new SimpleIntegerProperty();
        this.cause = new SimpleStringProperty();
        this.description = new SimpleStringProperty();

    }

    @Id
    @Column(name = "aID")
    @GeneratedValue
    public int getaID() {
        return aID.get();
    }

    public SimpleIntegerProperty aIDProperty() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID.set(aID);
    }


    //Date
    @Column(name = "description")
    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Column(name = "cause")
    public String getCause() {
        return cause.get();
    }

    public SimpleStringProperty causeProperty() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }
}
