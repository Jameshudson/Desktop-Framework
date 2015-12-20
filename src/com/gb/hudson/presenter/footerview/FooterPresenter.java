package com.gb.hudson.presenter.footerview;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import javax.inject.Inject;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by james on 13/12/2015.
 */
public class FooterPresenter implements Initializable {

    @FXML
    Text selectedPrinter;

    @Inject
    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        model.AddListener(PrinterService.PRINTER_SELECTED, (data) ->{

            String printerName = (String) data.get(PrinterService.PRINTER_SELECTED);

            selectedPrinter.getStyleClass().clear();
            selectedPrinter.getStyleClass().add("ok");
            selectedPrinter.setText(printerName);

            return true;
        });

        model.AddListener(PrinterService.NO_PRINTER_SELECTED, (data) -> {

            selectedPrinter.getStyleClass().clear();
            selectedPrinter.getStyleClass().add("bad");
            selectedPrinter.setText("No Printer Selected!");

            return true;
        });

        model.AddListener(PrinterService.ERROR_PRINT_JOB, (data) -> {

            selectedPrinter.getStyleClass().clear();
            selectedPrinter.getStyleClass().add("bad");
            selectedPrinter.setText("An Error occurred!");

            return true;
        });
    }
}
