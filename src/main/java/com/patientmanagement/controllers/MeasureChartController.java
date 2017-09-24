package com.patientmanagement.controllers;


import com.EntityClasses.Measure;
import com.EntityClasses.MeasureValue;
import com.appointmentscheduling.controllers.AppointmentScreens;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MeasureChartController implements ControlledScreen, Initializable {

    ScreenController controller;

    @FXML
    private LineChart<?, ?> measuresChart;

    @FXML
    private Label chartLabel;

    private CategoryAxis x;

    private NumberAxis y;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chartLabel.setText(PatientSummaryController.selectedMeasure.getName());

        XYChart.Series series = new XYChart.Series();

        for (MeasureValue m: PatientSummaryController.selectedMeasure.getValues()) {

            series.getData().add(new XYChart.Data(m.getDate().toString(), m.getValue()));
        }

        measuresChart.getData().addAll(series);

    }
}
