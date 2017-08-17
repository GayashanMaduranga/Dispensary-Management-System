package com.main.models;

import com.common.AlertDialog;
import com.common.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Damsith on 8/12/2017.
 */
public class LoginModel {

    private Connection connection;
    private ResultSet rs;

    public LoginModel(){
        connection = DBConnect.getConnection();
        if(connection == null){
            AlertDialog.show("", "Connection to database unsuccessful");
            System.exit(1);
        }
    }

    public ResultSet getValidatedUser(String username, String password){

        String query = "SELECT * FROM user WHERE username = '"+username+"' AND password = '"+password+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

        } catch (Exception e) {
            AlertDialog.show("Exception", e.toString());
        }

        return rs;

    }

}
