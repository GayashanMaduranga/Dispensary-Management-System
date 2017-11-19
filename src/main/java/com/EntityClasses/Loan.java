package com.EntityClasses;

import javafx.beans.property.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by gayashan on 9/13/2017.
 */
@Entity
public class Loan {

    private IntegerProperty id;
    private StringProperty description;
    private DoubleProperty loanAmount;
    private Date startDate;
    private Date endDate;
    private DoubleProperty interestRate;
    private DoubleProperty collectedLoan;
    private DoubleProperty collectedInterest;

    public Loan() {
        this.id = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty();
        this.loanAmount = new SimpleDoubleProperty();
        this.interestRate = new SimpleDoubleProperty();
        this.collectedLoan = new SimpleDoubleProperty();
        this.collectedInterest = new SimpleDoubleProperty();

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

    public String getDescription() {
        return description.get();
    }

    public StringProperty reasonProperty() {
        return description;
    }

    public void setDescription(String reason) {
        this.description.set(reason);
    }

    public double getLoanAmount() {
        return loanAmount.get();
    }

    public DoubleProperty loanAmountProperty() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount.set(loanAmount);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getInterestRate() {
        return interestRate.get();
    }

    public DoubleProperty interestRateProperty() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate.set(interestRate);
    }

    public double getCollectedLoan() {
        return collectedLoan.get();
    }

    public DoubleProperty collectedLoanProperty() {
        return collectedLoan;
    }

    public void setCollectedLoan(double collectedLoan) {
        this.collectedLoan.set(collectedLoan);
    }

    public double getCollectedInterest() {
        return collectedInterest.get();
    }

    public DoubleProperty collectedInterestProperty() {
        return collectedInterest;
    }

    public void setCollectedInterest(double collectedInterest) {
        this.collectedInterest.set(collectedInterest);
    }
}
