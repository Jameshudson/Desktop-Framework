package com.gb.hudson.presenter.settingview;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by james on 30/12/2015.
 */
public class SettingPresenter implements Initializable {

    @FXML
    CheckBox invertCheckBox;

    @FXML
    CheckBox insertCheckBox;

    @Inject
    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(invertCheckBox.selectedProperty().get() == true){

            ((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).invertPages(invertCheckBox.selectedProperty().get());
        }

    }

    public void insertCoverPageAction(){

        ((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).insertCoverPages(insertCheckBox.selectedProperty().get());
    }

    public void InvertPageAction(){

        ((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).invertPages(invertCheckBox.selectedProperty().get());
    }
}
