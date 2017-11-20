package com.suppliermanagement.controllers.Stock_Control_CTRL;

import com.EntityClasses.Email_account;
import com.common.AlertDialog;
import com.common.ScreenController;
import org.hibernate.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by Naveen Luke Fernando on 2017-11-19.
 */
public class EmailSend {

    org.hibernate.Session session;

    private String username;
    private String pass;




    EmailSend(String to , String sub,String msg)
    {

        session = ScreenController.getSession();
        session.beginTransaction();
        org.hibernate.Query email = session.createQuery("select s from Email_account s");
        //noinspection uncheckedi

        List <Email_account> em = email.list();
        session.getTransaction().commit();


        for (Email_account po : em) {
            System.out.println(po.getUsername());
            System.out.println(po.getPassword());

            username = po.getUsername();
            pass = po.getPassword();

            System.out.println(username +" "+pass);

        }






        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust","smtp.gmail.com");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getDefaultInstance(props,


                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication ()
                    {
                        return new PasswordAuthentication("peoplesdispensary.kandy@gmail.com", "itp@sliit");

                    }

                }
        );


        try {

            Message message = new MimeMessage (session);
            message.setFrom(new InternetAddress("peoplesdispensary.kandy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);
            Transport.send(message);
            System.out.println("message sent !!");



        } catch (Exception e) {


            System.out.println(e);


        }



    }






}
