package com.EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Embeddable;

/**
 * Created by gayashan on 9/17/2017.
 */
@Embeddable
public class Address {

    private StringProperty unitNO;
    private StringProperty streetAddress;
    private StringProperty city;
    private StringProperty zip;


    public Address() {
        this.unitNO = new SimpleStringProperty();
        this.streetAddress = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.zip = new SimpleStringProperty();
    }

    public String getUnitNO() {
        return unitNO.get();
    }

    public StringProperty unitNOProperty() {
        return unitNO;
    }

    public void setUnitNO(String unitNO) {
        this.unitNO.set(unitNO);
    }

    public String getStreetAddress() {
        return streetAddress.get();
    }

    public StringProperty streetAddressProperty() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress.set(streetAddress);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getZip() {
        return zip.get();
    }

    public StringProperty zipProperty() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }
}
