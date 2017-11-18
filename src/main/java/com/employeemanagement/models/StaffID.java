package com.employeemanagement.models;

import com.EntityClasses.Employee;
import com.employeemanagement.models.QrCode;
import com.google.zxing.WriterException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import com.itextpdf.text.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import org.apache.poi.hslf.model.Slide;
//import org.apache.poi.hslf.usermodel.SlideShow;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;

public class StaffID {

    String id;
    String name;
    BufferedImage image;
    Date date;
    //JFileChooser chooser;
    JFrame frame;
    QrCode qrCode;


    public StaffID(Employee emp) {
//        chooser = new JFileChooser();
        qrCode = new QrCode();
        this.id = String.valueOf(emp.getEmployeeid());
        this.name = emp.getName();
        this.image=emp.getImage();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 2);
        this.date = c.getTime();


    }


    //75-75
    private Image getScaledImage(){

        //Toolkit tk = Toolkit.getDefaultToolkit();
        try{

            Image scl = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            return scl;
        }catch(Exception e){
            return null;
        }


    }

    private BufferedImage createLibraryCardFront() throws IOException{

        //Date date = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(ft.format(date));

        BufferedImage bufferedImage = new BufferedImage(400, 225, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.black);
        //BufferedImage img = ImageIO.read(getClass().getResource("/com/image/background.jpg"));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Reports\\LibCardFACE.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);

        g.drawImage(getScaledImage(), 300 ,25, null);


        //System.out.println("ok");
        //g.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 300 ,25

        g.drawString(id, 25, 125);
        g.drawString(name, 25, 150);
        g.drawString(ft.format(date), 25, 175);


        return bufferedImage;

    }


    private BufferedImage createLibraryCardBack(String id) throws IOException, WriterException{



        BufferedImage bufferedImage = new BufferedImage(400, 225, BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.getGraphics();

        g.setColor(Color.black);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Reports\\LibCardBACK.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);

        //g.drawImage(ImageIO.read(getClass().getResource("/com/image/LibCardBACK.png")), 0, 0, null);

        //qrCode.encript("Test", id + ".png", 150, 150);

        g.drawImage(qrCode.encript(id, name, 150, 150), 120 ,40, null);




        return bufferedImage;

    }



    public void saveStraffIdImages(File file){


        if(file != null){

            try {

                Document doc=new Document(PageSize.A4);
                PdfWriter.getInstance(doc, new  FileOutputStream(file.getAbsolutePath()));
                doc.open();


                BufferedImage idFront = createLibraryCardFront();
                BufferedImage idBack = createLibraryCardBack(id);

                String fileNameFront="C:/Reports/front.png";
                String fileNameBack="C:/Reports/back.png";

                FileOutputStream out = new FileOutputStream(fileNameFront);
                javax.imageio.ImageIO.write(idFront, "png", out);
                out = new FileOutputStream(fileNameBack);
                javax.imageio.ImageIO.write(idBack, "png", out);

                out.flush();
                out.close();
                com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance(fileNameFront);
                //doc.setPageSize(new com.itextpdf.text.Rectangle(1080,720));
                doc.newPage();
                //image.setAbsolutePosition(0, 0);
                doc.add(image);
                doc.add( Chunk.NEWLINE );
                doc.add( Chunk.NEWLINE );
               doc.add(new Paragraph(Chunk.NEWLINE));
                doc.add( Chunk.NEWLINE );
                doc.add( Chunk.NEWLINE );
                image =com.itextpdf.text.Image.getInstance(fileNameBack);
                doc.add(image);
                doc.close();


                Platform.runLater(() ->  Notifications.create()
                        .title("Staff ID")
                        .text("successfully Created !!!")
                        .showInformation());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }





    }


}
