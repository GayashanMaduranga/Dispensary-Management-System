package com.Laboratory.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmilaWC on 11/20/2017.
 */
public class ResultReport {

    File filepath;


    String pid;
    String name;
    String Pemail;





    public ResultReport(File filepath) {

        this. filepath =  filepath;

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPemail() {
        return Pemail;
    }

    public void setPemail(String pemail) {
        Pemail = pemail;
    }



    public void genarateLabTest(){


        try {
            OutputStream file = new FileOutputStream(filepath);
            Paragraph para = new Paragraph();
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            Font font1 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
            Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            para = new Paragraph("The People's Dispensary", font1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Medical Center\n" +
                    "Kandy", font2);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);




            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);




            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.close();
            file.close();
            System.out.println("Pdf generated");



            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
