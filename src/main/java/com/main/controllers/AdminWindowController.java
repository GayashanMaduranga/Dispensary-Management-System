package com.main.controllers;


import com.EntityClasses.Patient;
import com.EntityClasses.User;
import com.common.AlertDialog;
import com.common.ConfirmDialog;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.patientmanagement.controllers.RegisterPatientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class AdminWindowController implements Initializable,SessionListener {

    private User p = new User();
    private User temp = new User();

//    ScreenController controller;

    private final ObservableList<User> userList = FXCollections.observableArrayList();



    private TreeItem<User> root;

    private Session session;
    private MainScreenController mainScreenController;

    @FXML
    private JFXTreeTableView<User> userTable;

    @FXML
    private Button getUserBtn;

    @FXML
    private Button removeUserBtn;

    @FXML
    private JFXButton addUserBtn;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private ComboBox<Integer> userAccessCombo;

    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();

        List<Integer> accessLevels = new ArrayList<>();
        accessLevels.add(1);
        accessLevels.add(2);
        accessLevels.add(3);
        accessLevels.add(4);
        accessLevels.add(5);
        accessLevels.add(6);
        accessLevels.add(7);
        accessLevels.add(8);

        userAccessCombo.getItems().addAll(accessLevels);

        session.beginTransaction();
        Query userNameQuery = session.createQuery("select u from User u");
        //noinspection unchecked
        List<User> users = userNameQuery.list();
        session.getTransaction().commit();

        for (User p : users){

            userList.add(p);
        }

        JFXTreeTableColumn<User, String> nameCol =  new JFXTreeTableColumn<>("Username");
        nameCol.setCellValueFactory(param -> param.getValue().getValue().usernameProperty());

        JFXTreeTableColumn<User, Number> accessCol =  new JFXTreeTableColumn<>("AccessLevel");
        accessCol.setCellValueFactory(param -> param.getValue().getValue().accessLevelProperty());

        root = new RecursiveTreeItem<>(userList, RecursiveTreeObject::getChildren);

        //noinspection unchecked
        userTable.getColumns().setAll(nameCol, accessCol);
        userTable.setRoot(root);
        userTable.setShowRoot(false);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> userTable.setPredicate(patientTreeItem -> {
            boolean flag = patientTreeItem.getValue().usernameProperty().getValue().contains(newValue.toLowerCase());
            return flag;
        }));

    }

    @FXML
    void setFields() {

        try {
            p = userTable.getSelectionModel().getSelectedItem().getValue();

            txtUsername.setText(p.getUsername());
            txtPassword1.setText(p.getPassword());
            txtPassword2.setText(p.getPassword());

        } catch (Exception e) {

            System.out.println("No row selected");
        }

    }


    private boolean passwordsMatch(){

        if(txtPassword1.getText().equals(txtPassword2.getText())){
            return true;
        }else{
            return false;
        }

    }

    @FXML
    void addUser(){

            if (passwordsMatch()) {

                User u = new User();
                u.setUsername(txtUsername.getText());
                u.setPassword(txtPassword1.getText());
                u.setAccessLevel(userAccessCombo.getSelectionModel().getSelectedItem());

                session.beginTransaction();
                session.save(u);
                session.getTransaction().commit();

                userList.add(u);
                userTable.refresh();

            } else {
                AlertDialog.show("", "The passwords do not match");
            }

    }

    @FXML
    void updateUser(){

        if (!(p.getUsername() == null)) {
            if (passwordsMatch()) {

                temp = p;

                userList.removeIf(user -> {
                    boolean flag = false;
                    if(user.getUsername() == p.getUsername())
                        flag = true;
                    return flag;
                });

                p.setUsername(txtUsername.getText());
                p.setPassword(txtPassword1.getText());
                p.setAccessLevel(userAccessCombo.getSelectionModel().getSelectedItem());

                session.beginTransaction();
                session.delete(temp);
                session.save(p);
                session.getTransaction().commit();

                userList.add(p);
                userTable.refresh();

            } else {
                AlertDialog.show("", "The passwords do not match");
            }
        }

    }

    @FXML
    void removeUser() {

        if (!(p.getUsername() == null)) {
            if(ConfirmDialog.show("", "Are you sure?")){

                session.beginTransaction();
                session.delete(p);
                session.getTransaction().commit();

                userList.remove(p);
                userTable.refresh();

                p = null;

                txtUsername.setText("");
                txtPassword1.setText("");
                txtPassword2.setText("");


            }
        }


    }

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage) addUserBtn.getScene().getWindow();
            s.close();
        }
    }

    @Override
    public void setSession(Session session) {
//        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;


    }
}
