/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author gayashan
 */
public class QrCode {
    
    Map map;
    String charset;
    String path;
    
            public QrCode() {
                
                map = new HashMap();
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                charset = "UTF-8";
                
                
	}

//	public void encript(String output, String path, int width, int height) throws WriterException,IOException {
//            
//		BitMatrix mat = new MultiFormatWriter().encode(new String(output.getBytes(charset), charset),BarcodeFormat.QR_CODE, width, height);
//                
//		MatrixToImageWriter.writeToFile(mat,path.substring(path.lastIndexOf('.') + 1), new File(path));
//                
//	}
        
        public BufferedImage encript(String output, String path, int width, int height){
            
        try {
            BitMatrix mat = new MultiFormatWriter().encode(new String(output.getBytes(charset), charset),BarcodeFormat.QR_CODE, width, height);
            return MatrixToImageWriter.toBufferedImage(mat);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(QrCode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(QrCode.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
        }

       
        public String decript(BufferedImage bufferedImage) throws NotFoundException{
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            
            Result result = new MultiFormatReader().decode(binaryBitmap,map);
           
            return result.getText();
        }
}
