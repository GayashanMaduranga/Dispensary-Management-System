package com.employeemanagement.models;

public class PayslipLoanDetail {
    String description;
    String instl;
    String inter;

    public PayslipLoanDetail(String description, String instl, String inter) {
        this.description = description;
        this.instl = instl;
        this.inter = inter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstl() {
        return instl;
    }

    public void setInstl(String instl) {
        this.instl = instl;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }
}
