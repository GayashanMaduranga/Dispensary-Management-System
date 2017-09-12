package com.EntityClasses;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * Created by gayashan on 9/12/2017.
 */
@Entity
@Access(AccessType.FIELD)
public class Staff extends Employee {


    @Column(name = "DATE_OF_APPOINTMENT",nullable = false)
    private Date dateOfAppointment;
    @Column(name = "QUALIFICATIONS")
    private String qualifications;

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    @Override
    public String getQualifications() {
        return qualifications;
    }

    @Override
    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
