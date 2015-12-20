package com.gb.hudson.presenter.printersettingview;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PrinterSettingPresenter implements Initializable {
	
	@FXML
	ChoiceBox<String> printerList;

	@FXML
	Button selectPrinterButton;
	
	@Inject
	Model model;

	public PrinterSettingPresenter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model.AddListener(PrinterService.PRINTERS_FOUND, (data) -> {
			
			List<String> printers = (List<String>)data.get("printers");

			for(int i = printers.size() - 1; i >= 0; i--){
			
				ObservableList<String> list = FXCollections.observableArrayList();
				
				list.add(printers.get(i));
				printerList.setItems(list);
			}
			return true;
		});
		
		((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).listPrinterNames();
	}
	
	public void setPrinterAction(){

		((PrinterService)model.getService(PrinterService.PRINTER_SERVICE_NAME)).selectPrinter(printerList.getValue());
	}

}
