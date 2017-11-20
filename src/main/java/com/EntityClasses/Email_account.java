package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Table(name = "Email_account")
public class Email_account  {


    private SimpleStringProperty username;
    private SimpleStringProperty password;


    public Email_account() {
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    @Id
    @Column(name = "username")
    //da@GeneratedValue
    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }


    @Column(name = "password")
    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}




