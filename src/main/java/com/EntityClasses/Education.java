package com.EntityClasses;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * Created by gayashan on 9/17/2017.
 */
@Embeddable
public class Education {
    StringProperty schoolName;
    StringProperty address;
    StringProperty phone;
    BooleanProperty isGraduated;
    Date fromDate;
    Date toDate;


    public Education() {
        this.schoolName = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.isGraduated = new SimpleBooleanProperty();

    }


    public String getSchoolName() {
        return schoolName.get();
    }

    public StringProperty schoolNameProperty() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName.set(schoolName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public boolean isIsGraduated() {
        return isGraduated.get();
    }

    public BooleanProperty isGraduatedProperty() {
        return isGraduated;
    }

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated.set(isGraduated);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
