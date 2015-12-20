package com.gb.hudson.presenter.printstarter;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by james on 13/12/2015.
 */
public class PrintStarterPresenter implements Initializable {


    @FXML
    Button PrintJobButton;

    @Inject
    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startPrintJob(){

        ((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).startPrintJob();
    }
}
