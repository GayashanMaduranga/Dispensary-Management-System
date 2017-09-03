package com.EntityClasses;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by gayashan on 8/27/2017.
 */

@Entity
public class Doctor {

    @Id
    private int employeeID;
    private String fullName;
    private String NIC;
    private String email;
    private char gender;
    private String contactNo;
    private Date dateOfBirth;
    private String qualifications;
    private String SLMC_reg_No;
    private double chargePerVisit;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getSLMC_reg_No() {
        return SLMC_reg_No;
    }

    public void setSLMC_reg_No(String SLMC_reg_No) {
        this.SLMC_reg_No = SLMC_reg_No;
    }

    public double getChargePerVisit() {
        return chargePerVisit;
    }

    public void setChargePerVisit(double chargePerVisit) {
        this.chargePerVisit = chargePerVisit;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "employeeID=" + employeeID +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
