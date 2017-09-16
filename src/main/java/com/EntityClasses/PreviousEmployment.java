package com.EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Embeddable;
import java.sql.Date;

/**
 * Created by gayashan on 9/16/2017.
 */
@Embeddable
public class PreviousEmployment {

    private StringProperty company;
    private StringProperty jobTitle;
    private StringProperty address;
    private StringProperty phone;
    private StringProperty supervisor;
    private Date fromDate;
    private Date toDate;


    public PreviousEmployment() {
        this.company = new SimpleStringProperty();
        this.jobTitle = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.supervisor = new SimpleStringProperty();
    }


    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
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

    public String getSupervisor() {
        return supervisor.get();
    }

    public StringProperty supervisorProperty() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor.set(supervisor);
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
