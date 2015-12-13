package com.gb.hudson.presenter.mainview;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.presenter.printview.PrintView;
import com.gb.hudson.presenter.tabview.TabView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainPresenter implements Initializable {
	
	@FXML
	AnchorPane left;
	
	@FXML
	AnchorPane right;
	
	@Inject
	Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//adding the print table
		PrintView printView = new PrintView();
		left.getChildren().add(printView.getView());
		
		TabView printerControls = new TabView();
		right.getChildren().add(printerControls.getView());
		
		model.init();
	}
}