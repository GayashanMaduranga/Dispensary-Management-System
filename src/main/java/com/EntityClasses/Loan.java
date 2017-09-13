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
    private StringProperty reason;
    private DoubleProperty loanAmount;
    private Date startDate;
    private Date endDate;
    private DoubleProperty interestRate;
    private DoubleProperty collectedLoan;


    public Loan() {
        this.id = new SimpleIntegerProperty();
        this.reason = new SimpleStringProperty();
        this.loanAmount = new SimpleDoubleProperty(0);
        this.interestRate = new SimpleDoubleProperty(0);
        this.collectedLoan = new SimpleDoubleProperty(0);

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

    public String getReason() {
        return reason.get();
    }

    public StringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
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
}
