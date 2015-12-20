package com.gb.hudson.presenter.printview;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

public class PrintPresenter implements Initializable{
	
	@FXML
	TableView<Map<String, String>> table;
	
	@Inject
	Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model.AddListener(PrinterService.PRINTERS_FOUND, (data) -> {
			

			return true;
		});
		
		
		PrinterService printer = (PrinterService)model.getService("PrintSerivce");
		
		printer.listPrinterNames();
	}

	public void dragDetected(){
		System.out.println("Testing");
	}

	public void test(DragEvent event){

		Dragboard db = event.getDragboard();

		if(db.hasFiles()){

			int nFiles = db.getFiles().size();

			for(int i = nFiles - 1; i >=0; i--) {

				System.out.println(db.getFiles().get(i).getPath());
			}
		}
	}
}
