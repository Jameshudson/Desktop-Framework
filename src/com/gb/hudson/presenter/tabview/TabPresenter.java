package com.gb.hudson.presenter.tabview;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.presenter.printersettingview.PrinterSettingView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class TabPresenter implements Initializable {
	
	@FXML
	TabPane tabs;
	
	@FXML
	AnchorPane printer;
	
	@FXML
	AnchorPane setting;
	
	@Inject
	Model model;

	public TabPresenter() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		PrinterSettingView printerSetting = new PrinterSettingView();
		printer.getChildren().add(printerSetting.getView());
	}
}