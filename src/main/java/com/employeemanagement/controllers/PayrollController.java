package com.employeemanagement.controllers;

import com.EntityClasses.Employee;
import com.common.SessionListener;
import com.employeemanagement.models.Payslip;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.hibernate.Session;
import com.main.controllers.MainScreenController;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PayrollController implements Initializable, SessionListener {


    @FXML
    private Button btnSave;

    @FXML
    private TextField txtEid;

    @FXML
    private TextField txtName;

    Employee emp;

    private Session session;
    MainScreenController mainController;
    private List<TreeItem<Allowance>> allowanceList = new ArrayList<>();
    private List<TreeItem<Decutions>> deductionList = new ArrayList<>();

    @FXML
    private TextField txtRateOfRemuneration;

    @FXML
    private TreeTableView<Allowance> tblAllowance;

    @FXML
    private TreeTableColumn<Allowance, String> colAllowance;

    @FXML
    private TextField txtAllowance;

    @FXML
    private TextField txtDeductions;

    @FXML
    private TreeTableView<Decutions> tblDeductions;

    @FXML
    private TreeTableColumn<Decutions, String> colDeductions;

    @FXML
    private TextField txtHours;

    @FXML
    private TextField txtOTRate;

    @FXML
    private TextField txtEPFbyEmployee;

    @FXML
    private TextField txtEPFbyEmployer;

    @FXML
    private DatePicker beginDate;

    @FXML
    private DatePicker endDate;

    @FXML
    void addAllowance(ActionEvent event) {


        Allowance allowance = new Allowance();
        allowance.setAllowance(txtAllowance.getText());

        allowanceList.add(new TreeItem<>(allowance));

        tblAllowance.getRoot().getChildren().clear();
        tblAllowance.getRoot().getChildren().addAll(allowanceList);

    }

    @FXML
    void addDeductions(ActionEvent event) {
        Decutions decutions = new Decutions();
        decutions.setDeduction(txtDeductions.getText());

        deductionList.add(new TreeItem<>(decutions));

        tblDeductions.getRoot().getChildren().clear();
        tblDeductions.getRoot().getChildren().addAll(deductionList);

    }

    @FXML
    void savePayslip(ActionEvent event) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(btnSave.getScene().getWindow());

        Payslip payslip = new Payslip(file);


       // payslip.setId(String.valueOf(emp.getEmployeeid()));
       // payslip.setName(emp.getName());
//        payslip.setDesignation(emp.getJobRole());
        payslip.setRateOfRemuneration(txtRateOfRemuneration.getText());


        List<String> alist= new ArrayList<>();
        for(TreeItem<Allowance> a : allowanceList){
            alist.add(a.getValue().getAllowance());
        }
        payslip.setAllowances(alist);

        List<String> dlist= new ArrayList<>();
        for(TreeItem<Allowance> a : allowanceList){
            dlist.add(a.getValue().getAllowance());
        }
        payslip.setDeductions(dlist);

        payslip.setOTHours(txtHours.getText());
        payslip.setEPFByEmployee(txtEPFbyEmployee.getText());
        payslip.setEPFByEmployer(txtEPFbyEmployer.getText());
        try {
            payslip.setMonth(DateTimeFormatter.ofPattern("MMMM").format(endDate.getValue()));
            payslip.setYear(DateTimeFormatter.ofPattern("yyyy").format(endDate.getValue()));
            payslip.setPayPeriodBeginDate(df.format(beginDate.getValue()));
            payslip.setPayPeriodEndDate(df.format(endDate.getValue()));
            double gross = Double.parseDouble(txtRateOfRemuneration.getText());

            for(TreeItem<Allowance> t : allowanceList){
               gross+=Double.parseDouble(t.getValue().getAllowance());
            }
            payslip.setGrossEarings(String.valueOf(gross));

            double epfByEmployee = gross * Double.parseDouble(txtEPFbyEmployee.getText())/100;
            payslip.setEPFByEmployee(String.valueOf(epfByEmployee));

            double epfByEmployer = gross * Double.parseDouble(txtEPFbyEmployer.getText())/100;
            payslip.setEPFByEmployer(String.valueOf(epfByEmployer));



        }catch (Exception e){
            e.printStackTrace();
        }


        payslip.genaratePayslip();

    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {
        this.mainController = (MainScreenController) controller;
       setData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();

    }

    private void setData(){
        emp = mainController.getEmployee();
        txtEid.setText(String.valueOf(emp.getEmployeeid()));
        txtName.setText(String.valueOf(emp.getName()));
    }

    private void initTable(){


        colAllowance.setCellValueFactory(param -> param.getValue().getValue().allowanceProperty());

        Allowance allowance = new Allowance();
        allowance.setAllowance("0000");

        TreeItem<Allowance> root1 = new TreeItem<>(allowance);

        tblAllowance.setRoot(root1);
        tblAllowance.setShowRoot(false);

        colDeductions.setCellValueFactory(param -> param.getValue().getValue().deductionProperty());

        Decutions decutions = new Decutions();
        decutions.setDeduction("0000");

        TreeItem<Decutions> root2 = new TreeItem<>(decutions);

        tblDeductions.setRoot(root2);
        tblDeductions.setShowRoot(false);


    }


    class Allowance{
        private StringProperty allowance;

        public Allowance() {
            allowance = new SimpleStringProperty();
        }

        public String getAllowance() {
            return allowance.get();
        }

        public StringProperty allowanceProperty() {
            return allowance;
        }

        public void setAllowance(String allowance) {
            this.allowance.set(allowance);
        }
    }

    class Decutions{
        private StringProperty deduction;

        public Decutions() {
            deduction = new SimpleStringProperty();
        }

        public String getDeduction() {
            return deduction.get();
        }

        public StringProperty deductionProperty() {
            return deduction;
        }

        public void setDeduction(String deduction) {
            this.deduction.set(deduction);
        }
    }
}
