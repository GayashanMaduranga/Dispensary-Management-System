package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Damsith on 8/12/2017.
 */
public class DBConnect {

    private static Connection connection = null;

    public static Connection getConnection(){

        if(connection != null)
            return connection;

        else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dispensary","root","");
                return connection;

            }catch (ClassNotFoundException|SQLException ex){

                System.out.println("Error: " + ex);
                return null;
            }
        }
    }
}
