package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;

/**
 * Created by gayashan on 9/13/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Payroll {

    private IntegerProperty id;
    private DoubleProperty allowances;
    private DoubleProperty OT_Rate;
    private DoubleProperty pensionByEmployer;
    private DoubleProperty pensionFromEmployee;
    private DoubleProperty deductions;
    private IntegerProperty remunerationPeriod;
    private DoubleProperty remunerationRate;


    public Payroll() {
        this.id = new SimpleIntegerProperty();
        this.allowances = new SimpleDoubleProperty();
        this.OT_Rate = new SimpleDoubleProperty();
        this.pensionByEmployer = new SimpleDoubleProperty();
        this.pensionFromEmployee = new SimpleDoubleProperty();
        this.deductions = new SimpleDoubleProperty();
        this.remunerationPeriod = new SimpleIntegerProperty();
        this.remunerationRate = new SimpleDoubleProperty();
    }


    @Id
    @GeneratedValue
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public double getAllowances() {
        return allowances.get();
    }

    public DoubleProperty allowancesProperty() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances.set(allowances);
    }

    public double getOT_Rate() {
        return OT_Rate.get();
    }

    public DoubleProperty OT_RateProperty() {
        return OT_Rate;
    }

    public void setOT_Rate(double OT_Rate) {
        this.OT_Rate.set(OT_Rate);
    }

    public double getPensionByEmployer() {
        return pensionByEmployer.get();
    }

    public DoubleProperty pensionByEmployerProperty() {
        return pensionByEmployer;
    }

    public void setPensionByEmployer(double pensionByEmployer) {
        this.pensionByEmployer.set(pensionByEmployer);
    }

    public double getPensionFromEmployee() {
        return pensionFromEmployee.get();
    }

    public DoubleProperty pensionFromEmployeeProperty() {
        return pensionFromEmployee;
    }

    public void setPensionFromEmployee(double pensionFromEmployee) {
        this.pensionFromEmployee.set(pensionFromEmployee);
    }

    public double getDeductions() {
        return deductions.get();
    }

    public DoubleProperty deductionsProperty() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions.set(deductions);
    }

    public int getRemunerationPeriod() {
        return remunerationPeriod.get();
    }

    public IntegerProperty remunerationPeriodProperty() {
        return remunerationPeriod;
    }

    public void setRemunerationPeriod(int remunerationPeriod) {
        this.remunerationPeriod.set(remunerationPeriod);
    }

    public double getRemunerationRate() {
        return remunerationRate.get();
    }

    public DoubleProperty remunerationRateProperty() {
        return remunerationRate;
    }

    public void setRemunerationRate(double remunerationRate) {
        this.remunerationRate.set(remunerationRate);
    }
}
