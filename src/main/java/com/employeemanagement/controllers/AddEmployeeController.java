package com.employeemanagement.controllers;

import com.EntityClasses.*;
import com.common.SessionListener;
import com.employeemanagement.models.AddEmployeeModel;
import com.employeemanagement.models.InputOutputModel;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.hibernate.Session;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import com.main.controllers.MainScreenController;

/**
 * Created by gayashan on 8/13/2017.
 */
@SuppressWarnings("Duplicates")
public class AddEmployeeController implements Initializable, SessionListener {


//    @FXML
//    private TextField empID;

    @FXML
    private DatePicker dateOfAppointment;

    @FXML
    private Circle empImage;

    @FXML
    private TextField fullName;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField nic;

    @FXML
    private TextField contactNumber;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TextField email;

    @FXML
    private TextField unitNo;

    @FXML
    private TextField streetAddress;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField zip;

    @FXML
    private MaskerPane testPane;

    @FXML
    private TreeTableView<PreviousEmployment> priviousEmployementTable;

    @FXML
    private TreeTableColumn<PreviousEmployment, String> colcompany;

    @FXML
    private TreeTableColumn<PreviousEmployment, String> coljobTitle;

    @FXML
    private TextField company;

    @FXML
    private TextField jobTitle;

    @FXML
    private DatePicker jobFrom;

    @FXML
    private TextField phone;

    @FXML
    private TextField supervisor;

    @FXML
    private TextField startingSalary;

    @FXML
    private TextField endingSalary;

    @FXML
    private DatePicker jobTo;

    @FXML
    private TextField companyAddress;

    @FXML
    private TreeTableView<Education> SchoolTable;

    @FXML
    private TreeTableColumn<Education, String> colSchoolName;

    @FXML
    private TreeTableColumn<Education, String> colSchoolAddress;

    @FXML
    private TextField schoolName;

    @FXML
    private JFXRadioButton yes;

    @FXML
    private JFXRadioButton no;

    @FXML
    private DatePicker schoolFrom;

    @FXML
    private TextField schoolPhone;

    @FXML
    private DatePicker schoolTo;

    @FXML
    private TextField schoolAddress;

    @FXML
    private ComboBox<String> cmbJobRole;

    @FXML
    private TreeTableView<EmploymentDetails> empDetailTbl;

    @FXML
    private TreeTableColumn<EmploymentDetails, String> colField;

    @FXML
    private TreeTableColumn<EmploymentDetails, String> colDetail;

    @FXML
    private TextField txtField;

    @FXML
    private TextArea txtDetails;
    private Session session;

    private final ToggleGroup genderGroup = new ToggleGroup();
    private final ToggleGroup yesNOGroup = new ToggleGroup();

    private FileChooser fileChooser;
    private Image empimage = null;
    private List<TreeItem<PreviousEmployment>> previouEmploymentList;
    private List<TreeItem<Education>> educationHistory;
    private List<TreeItem<EmploymentDetails>> employmentDetailsList;
    private BufferedImage employeeBufferedImage;
    private MainScreenController mainScreenController;
    File selectedFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //maskthis();
        initMainComponents();
        //Initialize Table Previous Employment
        initTables();
        validate();
        //maskthis();
    }


    @FXML
    void addEducationHistory(ActionEvent event) {


        try {
            Education education = new Education();
            education.setSchoolName(schoolName.getText());
            education.setAddress(schoolAddress.getText());
            education.setPhone(schoolPhone.getText());
            education.setFromDate(Date.valueOf(schoolFrom.getValue()));
            education.setToDate(Date.valueOf(schoolTo.getValue()));
            if (yes.isSelected()) {
                education.setIsGraduated("Y");
            } else {
                education.setIsGraduated("N");
            }

            educationHistory.add(new TreeItem<>(education));
            SchoolTable.getRoot().getChildren().clear();
            SchoolTable.getRoot().getChildren().addAll(educationHistory);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all required fields !");

            alert.showAndWait();
        }


    }

    @FXML
    void addNewStaff(ActionEvent event) {

        testPane.setVisible(true);
        Employee s = null;


        try {
            if (cmbJobRole.getSelectionModel().getSelectedItem().matches("doctor")) {
                s = new Doctor();
            } else {
                s = new Staff();
            }


            for (TreeItem<PreviousEmployment> p : previouEmploymentList
                    ) {

                s.getPreviousEmploymentList().add(p.getValue());
            }


            for (TreeItem<EmploymentDetails> p : employmentDetailsList
                    ) {

                s.getEmploymentDetails().add(p.getValue());
            }

            s.setName(fullName.getText());
            s.setDateOfAppointment(Date.valueOf(dateOfAppointment.getValue()));
//            s.setEmployeeid(Integer.parseInt(empID.getText()));


            if (selectedFile != null)
                s.setImageByte(InputOutputModel.readByteImage(selectedFile));
            else {

            }

            s.setEmail(email.getText());
            s.setDateOfBirth(Date.valueOf(dob.getValue()));
            s.setContactNumber(contactNumber.getText());
            s.setJobRole(cmbJobRole.getSelectionModel().getSelectedItem());
            s.setNic(nic.getText());
            if (male.isSelected()) {
                s.setGender("M");
            } else {
                s.setGender("F");
            }

            for (TreeItem<Education> e : educationHistory) {
                s.getEducationList().add(e.getValue());
            }

            Address address = new Address();
            address.setUnitNO(unitNo.getText());
            address.setStreetAddress(streetAddress.getText());
            address.setCity(city.getText());
            address.setZip(zip.getText());

            s.setAddress(address);

            AddEmployeeModel.addEmployee(s);


        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all required fields !");

            alert.showAndWait();
            //e.printStackTrace();

        }


        testPane.setVisible(false);
        resetAll();
    }

    @FXML
    void addPreviousEmployment(ActionEvent event) {

        try {
            PreviousEmployment emplymemt = new PreviousEmployment();
            emplymemt.setCompany(company.getText());
            emplymemt.setAddress(companyAddress.getText());
            emplymemt.setJobTitle(jobTitle.getText());
            emplymemt.setPhone(phone.getText());
            emplymemt.setSupervisor(supervisor.getText());
            emplymemt.setFromDate(Date.valueOf(jobFrom.getValue()));
            emplymemt.setToDate(Date.valueOf(jobTo.getValue()));
            emplymemt.setStartingSalary(Double.parseDouble(startingSalary.getText()));
            emplymemt.setEndingSalary(Double.parseDouble(endingSalary.getText()));


            previouEmploymentList.add(new TreeItem<>(emplymemt));
            priviousEmployementTable.getRoot().getChildren().clear();
            priviousEmployementTable.getRoot().getChildren().addAll(previouEmploymentList);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all required fields !");
            alert.showAndWait();
        }

    }

    @FXML
    void addEmpDetails(ActionEvent event) {

        System.out.println("HELLO");
        try {
            EmploymentDetails details = new EmploymentDetails();
            details.setField(txtField.getText());
            details.setDetails(txtDetails.getText());

            employmentDetailsList.add(new TreeItem<>(details));
            empDetailTbl.getRoot().getChildren().clear();
            empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all required fields !");

            alert.showAndWait();
        }


    }

    @FXML
    void uplodePhoto(ActionEvent event) {

        testPane.setVisible(true);
        fileChooser.setTitle("Select Employee Image");


        selectedFile = fileChooser.showOpenDialog(null);

        try {

            empimage = new Image(selectedFile.toURI().toString());
            empImage.setFill(new ImagePattern(empimage));
            employeeBufferedImage = ImageIO.read(selectedFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        testPane.setVisible(false);
    }

    @FXML
    void UpdatePreviousEmployment(ActionEvent event) {


        TreeItem<PreviousEmployment> employment = priviousEmployementTable.getSelectionModel().getSelectedItem();
//        previouEmploymentList.remove(employment);
        employment.getValue().setCompany(company.getText());
        employment.getValue().setAddress(companyAddress.getText());
        employment.getValue().setJobTitle(jobTitle.getText());
        employment.getValue().setPhone(phone.getText());
        employment.getValue().setSupervisor(supervisor.getText());
        employment.getValue().setFromDate(Date.valueOf(jobFrom.getValue()));
        employment.getValue().setToDate(Date.valueOf(jobTo.getValue()));

//        previouEmploymentList.add(employment);

        priviousEmployementTable.getRoot().getChildren().clear();
        priviousEmployementTable.getRoot().getChildren().addAll(previouEmploymentList);
    }


    @FXML
    void RemovePreviousEmployment(ActionEvent event) {

//        PreviousEmployment employment = priviousEmployementTable.getSelectionModel().getSelectedItem().getValue();
        previouEmploymentList.remove(priviousEmployementTable.getSelectionModel().getSelectedItem());
        priviousEmployementTable.getRoot().getChildren().clear();
        priviousEmployementTable.getRoot().getChildren().addAll(previouEmploymentList);


    }


    @FXML
    void removeEducationHistory(ActionEvent event) {

        educationHistory.remove(SchoolTable.getSelectionModel().getSelectedItem());
        SchoolTable.getRoot().getChildren().clear();
        SchoolTable.getRoot().getChildren().addAll(educationHistory);

    }

    @FXML
    void removeEmpDetails(ActionEvent event) {

        employmentDetailsList.remove(empDetailTbl.getSelectionModel().getSelectedItem());
        empDetailTbl.getRoot().getChildren().clear();
        empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);

    }

    @FXML
    void updateEducationHistory(ActionEvent event) {

        TreeItem<Education> item = SchoolTable.getSelectionModel().getSelectedItem();
        item.getValue().setSchoolName(schoolName.getText());
        item.getValue().setAddress(schoolAddress.getText());
        item.getValue().setPhone(schoolPhone.getText());
        item.getValue().setFromDate(Date.valueOf(schoolFrom.getValue()));
        item.getValue().setToDate(Date.valueOf(schoolTo.getValue()));
        if (yes.isSelected()) {
            item.getValue().setIsGraduated("Y");
        } else {
            item.getValue().setIsGraduated("N");
        }

        SchoolTable.getRoot().getChildren().clear();
        SchoolTable.getRoot().getChildren().addAll(educationHistory);
    }

    @FXML
    void previousEmployementTableSelections(MouseEvent event) {

        PreviousEmployment employment = priviousEmployementTable.getSelectionModel().getSelectedItem().getValue();
        company.setText(employment.getCompany());
        jobTitle.setText(employment.getJobTitle());
        companyAddress.setText(employment.getAddress());
        phone.setText(employment.getPhone());
        supervisor.setText(employment.getSupervisor());
        startingSalary.setText(String.valueOf(employment.getStartingSalary()));
        endingSalary.setText(String.valueOf(employment.getEndingSalary()));
        jobFrom.setUserData(employment.getFromDate().toLocalDate());
        jobTo.setUserData(employment.getToDate().toLocalDate());

    }


    @FXML
    void educationTableSelection(MouseEvent event) {
        Education education = SchoolTable.getSelectionModel().getSelectedItem().getValue();


        schoolName.setText(education.getSchoolName());
        schoolAddress.setText(education.getAddress());
        schoolPhone.setText(education.getPhone());
        schoolFrom.setValue(education.getFromDate().toLocalDate());
        schoolTo.setValue(education.getFromDate().toLocalDate());


    }

    @FXML
    void empDetailTblSelection(MouseEvent event) {

        EmploymentDetails details = empDetailTbl.getSelectionModel().getSelectedItem().getValue();

        txtField.setText(details.getField());
        txtDetails.setText(details.getDetails());
    }


    private void initTables() {

        colcompany.setCellValueFactory(param -> param.getValue().getValue().companyProperty());
        coljobTitle.setCellValueFactory(param -> param.getValue().getValue().jobTitleProperty());

        PreviousEmployment employment = new PreviousEmployment();
        employment.setCompany("company");
        employment.setJobTitle("Job Title");
        TreeItem<PreviousEmployment> root1 = new TreeItem<>();


        priviousEmployementTable.setRoot(root1);
        priviousEmployementTable.setShowRoot(false);


        colSchoolName.setCellValueFactory(param -> param.getValue().getValue().schoolNameProperty());
        colSchoolAddress.setCellValueFactory(param -> param.getValue().getValue().addressProperty());

        Education education = new Education();
        education.setSchoolName("School Name");
        education.setAddress("Address");
        TreeItem<Education> root2 = new TreeItem<>();

        SchoolTable.setRoot(root2);
        SchoolTable.setShowRoot(false);

        colField.setCellValueFactory(param -> param.getValue().getValue().fieldProperty());
        colDetail.setCellValueFactory(param -> param.getValue().getValue().detailsProperty());

        EmploymentDetails employmentDetails = new EmploymentDetails();
        employmentDetails.setField("field");
        employmentDetails.setDetails("Details");

        TreeItem<EmploymentDetails> root3 = new TreeItem<>();
        empDetailTbl.setRoot(root3);
        empDetailTbl.setShowRoot(false);


    }

    private void initMainComponents() {
        fileChooser = new FileChooser();
        empImage.setFill(new ImagePattern(new Image("/com/Images/user1600.png")));
        previouEmploymentList = new ArrayList<>();
        educationHistory = new ArrayList<>();
        employmentDetailsList = new ArrayList<>();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        yes.setToggleGroup(yesNOGroup);
        no.setToggleGroup(yesNOGroup);

    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController) controller;


    }

    private void validate() {
        ValidationSupport validationSupport = new ValidationSupport();

        //  validationSupport.registerValidator(empID, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(fullName, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(nic, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(contactNumber, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(email, Validator.createEmptyValidator("Text is required"));

//        validationSupport.registerValidator(unitNo, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(streetAddress, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(city, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(zip, Validator.createEmptyValidator("Text is required"));

        validationSupport.registerValidator(cmbJobRole, Validator.createEmptyValidator("ComboBox Selection required"));

        validationSupport.registerValidator(dob, Validator.createEmptyValidator("Text is required"));
        validationSupport.registerValidator(dateOfAppointment, Validator.createEmptyValidator("Text is required"));

    }

    @FXML
    void resetFields(ActionEvent event) {

        testPane.setVisible(true);

        resetAll();

        testPane.setVisible(false);

    }

    private void resetAll() {
//    empID.setText("");
        fullName.setText("");
        nic.setText("");
        contactNumber.setText("");
        email.setText("");
        unitNo.setText("");
        streetAddress.setText("");
        city.setText("");
        zip.setText("");
        cmbJobRole.getSelectionModel().clearSelection();
        dob.setValue(null);
        dateOfAppointment.setValue(null);
        selectedFile = null;
        empImage.setFill(new ImagePattern(new Image("/com/Images/user1600.png")));


        previouEmploymentList.clear();
        priviousEmployementTable.getRoot().getChildren().clear();
        company.setText("");
        jobTitle.setText("");
        companyAddress.setText("");
        phone.setText("");
        supervisor.setText("");
        startingSalary.setText("");
        endingSalary.setText("");
        jobFrom.setUserData("");
        jobTo.setUserData("");

        educationHistory.clear();
        SchoolTable.getRoot().getChildren().clear();
        schoolName.setText("");
        schoolAddress.setText("");
        schoolPhone.setText("");
        schoolFrom.setValue(null);
        schoolTo.setValue(null);

        employmentDetailsList.clear();
        empDetailTbl.getRoot().getChildren().clear();

        txtField.setText("");
        txtDetails.setText("");
    }

    @FXML
    void UpdateEmpDetails(ActionEvent event) {

        TreeItem<EmploymentDetails> details = empDetailTbl.getSelectionModel().getSelectedItem();
        details.getValue().setField(txtField.getText());
        details.getValue().setDetails(txtDetails.getText());
        empDetailTbl.getRoot().getChildren().clear();
        empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);

    }

    @FXML
    void RemoveEmpDetails(ActionEvent event) {
        employmentDetailsList.remove(empDetailTbl.getSelectionModel().getSelectedItem());
        empDetailTbl.getRoot().getChildren().clear();
        empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);
    }


}
