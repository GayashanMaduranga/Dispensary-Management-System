package com.employeemanagement.controllers;

import com.EntityClasses.*;
import com.common.SessionListener;
import com.employeemanagement.models.AttendanceImpl;
import com.employeemanagement.models.ViewEmployeeModel;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.LocalTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */

@SuppressWarnings("Duplicates")
public class ViewEmployeeController implements Initializable,SessionListener {


    //////////##########################

    @FXML
    private TextField empID;

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

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab attendanceTab;

    @FXML
    private Agenda agenda;

    @FXML
    private CalendarPicker calendar;

    @FXML
    private TreeTableView<Loan> loanTable;

    @FXML
    private TreeTableColumn<Loan, Number> colLoanID;

    @FXML
    private TreeTableColumn<Loan, Number> colLoanAmount;

    @FXML
    private TextField txtLoanAmount;

    @FXML
    private TextField txtInterest;

    @FXML
    private DatePicker loanFrom;

    @FXML
    private DatePicker loanTo;

    @FXML
    private JFXTextField txtLoanDescription;

    @FXML
    private AnchorPane attendanceAcPane;

    private ViewEmployeeModel viewEmployeeModel = new ViewEmployeeModel();

    ////////////#######################


    private Session session;

    private final ToggleGroup genderGroup = new ToggleGroup();
    private final ToggleGroup yesNOGroup = new ToggleGroup();

    private FileChooser fileChooser;
    private Image empimage = null;
    private List<TreeItem<PreviousEmployment>> previouEmploymentList;
    private List<TreeItem<Education>> educationHistory;
    private List<TreeItem<EmploymentDetails>> employmentDetailsList;
    private BufferedImage employeeBufferedImage;
    File selectedFile;
    private MainScreenController mainController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initMainComponents();
        //Initialize Table Previous Employment
        initTables();




    }


    @FXML
    void uplodePhoto(ActionEvent event) {
        fileChooser.setTitle("Select Employee Image");


//        fileChooser.getExtensionFilters().addAll(
//
//                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));


        selectedFile = fileChooser.showOpenDialog(null);

        try {

            empimage = new Image(selectedFile.toURI().toString());
            empImage.setFill(new ImagePattern(empimage));
            employeeBufferedImage = ImageIO.read(selectedFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
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


        if (education.getIsGraduated().matches("Y")) {
            yes.setSelected(true);
        } else {

            no.setSelected(true);
        }

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
        this.mainController = (MainScreenController) controller;
        setData();
        if(mainController.getEmployee().getJobRole().matches("doctor")) {
            attendanceAcPane.getChildren().clear();
        }else {
            initAgenda();
        }
    }

    private void setData() {

        Employee staff = (Employee) mainController.getEmployee();
        empID.setText(String.valueOf(staff.getEmployeeid()));
        fullName.setText(staff.getName());
        dateOfAppointment.setValue(staff.getDateOfAppointment().toLocalDate());
        email.setText(staff.getName());
        dob.setValue(staff.getDateOfBirth().toLocalDate());
        contactNumber.setText(staff.getContactNumber());
        cmbJobRole.setValue(staff.getJobRole());
        //jobRole.setText(staff.getJobRole());
        unitNo.setText(staff.getAddress().getUnitNO());
        nic.setText(staff.getNic());
        streetAddress.setText(staff.getAddress().getStreetAddress());
        city.setText(staff.getAddress().getCity());
        zip.setText(staff.getAddress().getZip());

        if (staff.getJobRole().matches("doctor")) {
            System.out.println("hello");
            tabPane.getChildrenUnmodifiable().clear();
        }


        if (staff.getGender().matches("M")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);

        }


        try {
            employeeBufferedImage = ImageIO.read(new ByteArrayInputStream(staff.getImageByte()));
            Image image = SwingFXUtils.toFXImage(employeeBufferedImage, null);
            empImage.setFill(new ImagePattern(image));
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (PreviousEmployment employment : staff.getPreviousEmploymentList()) {
            previouEmploymentList.add(new TreeItem<>(employment));
        }
        priviousEmployementTable.getRoot().getChildren().clear();
        priviousEmployementTable.getRoot().getChildren().addAll(previouEmploymentList);


        for (Education e : staff.getEducationList()) {
            educationHistory.add(new TreeItem<>(e));
        }

        SchoolTable.getRoot().getChildren().clear();
        SchoolTable.getRoot().getChildren().addAll(educationHistory);


        for (EmploymentDetails e : staff.getEmploymentDetails()) {
            employmentDetailsList.add(new TreeItem<>(e));
        }


        empDetailTbl.getRoot().getChildren().clear();
        empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);


//        Staff s = new Staff();
//
//        for ( TreeItem<PreviousEmployment> p: previouEmploymentList
//                ) {
//
//            s.getPreviousEmploymentList().add(p.getValue());
//        }
//
//        s.setName(fullName.getText());
//        s.setDateOfAppointment(Date.valueOf(dateOfAppointment.getValue()));
//        s.setEmployeeid(Integer.parseInt(empID.getText()));
//        s.setImage(employeeBufferedImage);
//        s.setEmail(email.getText());
//        s.setDateOfBirth(Date.valueOf(dob.getValue()));
//        s.setContactNumber(contactNumber.getText());
//        s.setJobRole(jobRole.getText());
//        if(male.isSelected()) {
//            s.setGender("M");
//        }else {
//            s.setGender("F");
//        }
//
//        for ( TreeItem<Education> e: educationHistory
//                ) {
//
//            s.getEducationList().add(e.getValue());
//        }
//
//        Address address = new Address();
//        address.setUnitNO(unitNo.getText());
//        address.setStreetAddress(streetAddress.getText());
//        address.setCity(city.getText());
//        address.setZip(zip.getText());
//
//        s.setAddress(address);
    }


    @FXML
    void empDetailTblSelection(MouseEvent event) {

        EmploymentDetails details = empDetailTbl.getSelectionModel().getSelectedItem().getValue();

        txtField.setText(details.getField());
        txtDetails.setText(details.getDetails());
    }

    @FXML
    void removeEmpDetails(ActionEvent event) {

        employmentDetailsList.remove(empDetailTbl.getSelectionModel().getSelectedItem());
        empDetailTbl.getRoot().getChildren().clear();
        empDetailTbl.getRoot().getChildren().addAll(employmentDetailsList);

    }

    @FXML
    void updateStaff(ActionEvent event) {

        Employee staff = mainController.getEmployee();


        staff.getEmploymentDetails().clear();
        staff.getEducationList().clear();
        staff.getPreviousEmploymentList().clear();

        for (TreeItem<PreviousEmployment> p : previouEmploymentList
                ) {

            staff.getPreviousEmploymentList().add(p.getValue());
        }

        for (TreeItem<EmploymentDetails> p : employmentDetailsList
                ) {

            staff.getEmploymentDetails().add(p.getValue());
        }


        staff.setName(fullName.getText());
        staff.setDateOfAppointment(Date.valueOf(dateOfAppointment.getValue()));
        staff.setEmployeeid(Integer.parseInt(empID.getText()));
        staff.setEmail(email.getText());
        staff.setDateOfBirth(Date.valueOf(dob.getValue()));
        staff.setContactNumber(contactNumber.getText());
        staff.setJobRole(cmbJobRole.getValue());
        staff.setNic(nic.getText());
        if (male.isSelected()) {
            staff.setGender("M");
        } else {
            staff.setGender("F");
        }

        for (TreeItem<Education> e : educationHistory
                ) {

            staff.getEducationList().add(e.getValue());
        }

        Address address = staff.getAddress();
        address.setUnitNO(unitNo.getText());
        address.setStreetAddress(streetAddress.getText());
        address.setCity(city.getText());
        address.setZip(zip.getText());

        staff.setAddress(address);

        ViewEmployeeModel.updateEmployee(staff);

    }


    @FXML
    void resetFields(ActionEvent event) {

        testPane.setVisible(true);

        resetAll();

        testPane.setVisible(false);

    }

    private void resetAll() {
        empID.setText("");
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



    private void initAgenda() {
        try {
            updateAgenda();
        }catch (Exception e){
            e.printStackTrace();

        }
        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set((localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal = new AttendanceImpl()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));


            int id = viewEmployeeModel.addNewAttendance(appointmentImplLocal, mainController.getEmployee());

            AttendanceImpl a = (AttendanceImpl) appointmentImplLocal;
            a.setId(id);

            return a;

        });


        calendar.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            java.util.Date cal = calendar.getCalendar().getTime();
            LocalDate ld = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalTime lt = LocalTime.NOON;

            agenda.setDisplayedLocalDateTime(LocalDateTime.of(ld, lt));
            updateAgenda();
        });


        agenda.appointmentChangedCallbackProperty().set(param ->{


                    viewEmployeeModel.updateAppointment((AttendanceImpl) param);
                    return null;
                }
        );


    }

    private void updateAgenda(){
        agenda.localDateTimeRangeCallbackProperty().set(param -> {


                    List<AttendanceImpl> list = viewEmployeeModel.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime(),mainController.getEmployee());
                    agenda.appointments().clear();
                    agenda.appointments().addAll(list);
                    return null;
                }

        );


    }

}
