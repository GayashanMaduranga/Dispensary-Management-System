package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

import javax.persistence.Entity;

/**
 * Created by gayashan on 9/13/2017.
 */

@Entity
public class Payroll {

    IntegerProperty id;
    DoubleProperty allowances;
    DoubleProperty OT_Rate;
    DoubleProperty pensionByEmployer;
    DoubleProperty PensionFromEmployee;

}
