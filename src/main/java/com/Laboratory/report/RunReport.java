package com.Laboratory.report;

import com.Laboratory.report.ResultReport;

import java.io.File;

/**
 * Created by AmilaWC on 11/20/2017.
 */
public class RunReport {



    public void canGenerateLabTest(){

        ResultReport r = new ResultReport( new File("C:\\New2.pdf"));

//        public static final String DEST = "results/test.pdf";


        r.setPid("1111");
        r.setName("Jude");
        r.setPemail("sdfsf@gmail.com");





        r.genarateLabTest();

    }


}
