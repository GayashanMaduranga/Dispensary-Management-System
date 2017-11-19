package com.employeemanagement.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Payslip {

    File  filepath;


    String id;
    String name;
    String designation;
    String daysWorked;
    String OTHours;
    String otPay;
    String netSal;
    String year;
    String month;
    String payPeriodBeginDate;
    String payPeriodEndDate;
    String EPFByEmployer;
    String EPFByEmployee;
    String grossSal;
    String rateOfRemuneration;
    List<String> allowances;
    String grossEarings;


    List<String> deductions;
    public Payslip(File filepath) {

        this. filepath =  filepath;
        this.allowances = new ArrayList<>();
        this.deductions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(String daysWorked) {
        this.daysWorked = daysWorked;
    }

    public String getOTHours() {
        return OTHours;
    }

    public void setOTHours(String OTHours) {
        this.OTHours = OTHours;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPayPeriodBeginDate() {
        return payPeriodBeginDate;
    }

    public void setPayPeriodBeginDate(String payPeriodBeginDate) {
        this.payPeriodBeginDate = payPeriodBeginDate;
    }

    public String getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(String payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public String getGrossSal() {
        return grossSal;
    }

    public void setGrossSal(String grossSal) {
        this.grossSal = grossSal;
    }





    public String getOtPay() {
        return otPay;
    }

    public void setOtPay(String otPay) {
        this.otPay = otPay;
    }
    public File getFilepath() {
        return filepath;
    }

    public void setFilepath(File filepath) {
        this.filepath = filepath;
    }

    public String getEPFByEmployer() {
        return EPFByEmployer;
    }

    public void setEPFByEmployer(String EPFByEmployer) {
        this.EPFByEmployer = EPFByEmployer;
    }

    public String getEPFByEmployee() {
        return EPFByEmployee;
    }

    public void setEPFByEmployee(String EPFByEmployee) {
        this.EPFByEmployee = EPFByEmployee;
    }

    public String getRateOfRemuneration() {
        return rateOfRemuneration;
    }

    public void setRateOfRemuneration(String rateOfRemuneration) {
        this.rateOfRemuneration = rateOfRemuneration;
    }

    public List<String> getAllowances() {
        return allowances;
    }

    public void setAllowances(List<String> allowances) {
        this.allowances = allowances;
    }

    public String getGrossEarings() {
        return grossEarings;
    }

    public void setGrossEarings(String grossEarings) {
        this.grossEarings = grossEarings;

    }

    public List<String> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<String> deductions) {
        this.deductions = deductions;
    }


    public void genaratePayslip(){


        try {
            OutputStream file = new FileOutputStream(filepath);
            Paragraph para ;
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            Font font1 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
            Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            para = new Paragraph("The People's Dispensary", font1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Medical Center\n" +
                    "Kandy", font2);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);


//            Calendar cal = Calendar.getInstance();
//            java.util.Date d = new java.util.Date(cal.getTimeInMillis());
//            String month = new SimpleDateFormat("MMMM").format(d);
//            int year = cal.get(Calendar.YEAR);
//            System.out.println("Month " + month);


            para = new Paragraph("Pay-slip for the month of " + month +" " + year,font1 );
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(3);
            table.getDefaultCell().setBorder(0);

            table.addCell(new Phrase("Employee ID ", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(id, font2));

            table.addCell(new Phrase("Name of Employee ", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(name, font2));

            table.addCell(new Phrase("Designation ", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(designation,font2));

            table.addCell(new Phrase("Pay Period Begin Date ", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(payPeriodBeginDate, font2));

            table.addCell(new Phrase("Pay Period End Date ", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(payPeriodEndDate, font2));

            table.addCell(new Phrase("daysWorked", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(daysWorked, font2));

            table.addCell(new Phrase("OTHours", font2));
            table.addCell(new Phrase(":", font2));
            table.addCell(new Phrase(OTHours, font2));

            table.addCell(new Phrase("", font2));
            table.addCell(new Phrase("", font2));
            table.addCell(new Phrase("", font2));


            document.add(table);
            document.add(Chunk.NEWLINE);


            PdfPTable table1 = new PdfPTable(4);

            table1.getDefaultCell().setBorderWidth(1);

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase("INCOME", font3));
            table1.addCell(new Phrase("AMOUNT ", font3));
            table1.addCell(new Phrase("DEDUCTIONS", font3));
            table1.addCell(new Phrase("AMOUNT", font3));

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("Rate Of Remuneration ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(rateOfRemuneration, font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("EPF by employer", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(EPFByEmployer, font3));

///////////////////////////////////////////////////////////////////////////////////////////

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("Allowance Payable", font3));
            PdfPTable allowanceTable = new PdfPTable(1);

            allowanceTable.getDefaultCell().setBorderWidth(1);

            for(String a : allowances){
                allowanceTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                allowanceTable.addCell(new Phrase(a, font3));

            }




            table1.addCell(allowanceTable);

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("EPF by employee", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(EPFByEmployee, font3));

            ////////////////////////////////////////////////////////////////////////////////

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));

            ////////////////////////////////////////////////////////////////////////////////

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));

//////////////////////////////////////////////////////////////////////////////////////////////////////////0
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("Gross Earnings ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(this.getGrossEarings(), font3));

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("Other Deductions ", font3));
           // table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            // table1.addCell(new Phrase(deductions, font3));


//            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//            table1.addCell(new Phrase("Deductions", font3));
//            PdfPTable deductionTable = new PdfPTable(1);
//
//            allowanceTable.getDefaultCell().setBorderWidth(1);
//
//            for(String a : deductions){
//                allowanceTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                allowanceTable.addCell(new Phrase(a, font3));
//
//            }
//
//
//
//
//            table1.addCell(allowanceTable);


            PdfPTable deductionTable = new PdfPTable(1);

            allowanceTable.getDefaultCell().setBorderWidth(1);

            for(String a : deductions){
                allowanceTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                allowanceTable.addCell(new Phrase(a, font3));

            }




            table1.addCell(deductionTable);

            ////////////////////////////////////////////////////////////////////////////////

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));


            ////////////////////////////////////////////////////////////////////////////////

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("OT Pay ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(this.getOtPay(), font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase(" ", font3));
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(new Phrase(" ", font3));
            document.add(table1);
            document.add(Chunk.NEWLINE);
//            document.add(Chunk.NEWLINE);
//            document.add(Chunk.NEWLINE);


            PdfPTable finalTable = new PdfPTable(1);
            finalTable.getDefaultCell().setBorder(1);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(new Phrase("Net salary : " + this.getNetSal(), font3));

            document.add(finalTable);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            para = new Paragraph("ITP-MET-WD-01",font1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.close();
            file.close();
            System.out.println("Pdf generated");


            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getNetSal() {
        return netSal;
    }

    public void setNetSal(String netSal) {
        this.netSal = netSal;
    }
}
