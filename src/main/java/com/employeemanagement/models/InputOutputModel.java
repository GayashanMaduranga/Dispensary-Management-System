package com.employeemanagement.models;

import java.io.File;
import java.io.FileInputStream;

public class InputOutputModel {


    public static byte[] readByteImage(File file){

        byte[] bFile = new byte[(int) file.length()];

        try {
            System.out.println(file.length());
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bFile;
    }

}
