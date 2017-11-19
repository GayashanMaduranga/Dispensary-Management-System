package com.suppliermanagement.controllers.Stock_Control_CTRL;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.util.Properties;

/**
 * Created by Naveen Luke Fernando on 2017-11-19.
 */
public class EmailSend {

    private String user = "peoplesdispensary.kandy@gmail.com";
    private String pass = "itp@sliit";




    EmailSend(String to , String sub)
    {
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
                        return new PasswordAuthentication("peoplesdispensary.kandy@gmail.com", "itp@sliit" );

                    }

                }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("peoplesdispensary.kandy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            //message.setText(msg);
            //message.setContent(msg1,"text/html");

            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String filename = "/C:/Users/Naveen Luke Fernando/Desktop/class.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("meesage sent");

        } catch (Exception e) {


            System.out.println(e);


        }



    }






}
