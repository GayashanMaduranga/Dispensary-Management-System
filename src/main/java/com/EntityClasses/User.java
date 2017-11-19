package com.EntityClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

/**
 * Created by gayashan on 8/28/2017.
 */

@Entity
@Table(name = "USERS")
@Access(AccessType.PROPERTY)
public class User {

    IntegerProperty id;
    IntegerProperty accessLevel;
    StringProperty username;
    StringProperty password;
    Employee employee;

    public User() {
        this.id = new SimpleIntegerProperty();
        this.accessLevel = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();

    }

    @Id
    @Column(name = "Username")
    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    @Column(name = "Password")
    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Column(name = "AccessLevel")
    public int getAccessLevel() {
        return accessLevel.get();
    }

    public IntegerProperty accessLevelProperty() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel.set(accessLevel);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}