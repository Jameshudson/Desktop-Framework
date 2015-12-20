package com.gb.hudson.presenter.mainview;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.presenter.footerview.FooterView;
import com.gb.hudson.presenter.printstarter.PrintStarterView;
import com.gb.hudson.presenter.printview.PrintView;
import com.gb.hudson.presenter.tabview.TabView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainPresenter implements Initializable {

    @FXML
	AnchorPane left;
	
	@FXML
    VBox right;

    @FXML
    AnchorPane dragPane;

    @FXML
    AnchorPane footer;

	@Inject
	Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//adding the print table
		PrintView printView = new PrintView();
		left.getChildren().add(printView.getView());
		
		TabView printerControls = new TabView();
		right.getChildren().add(printerControls.getView());

        PrintStarterView printStarter = new PrintStarterView();
        right.getChildren().add(printStarter.getView());

        FooterView footerView = new FooterView();
        footer.getChildren().add(footerView.getView());

		model.init();
	}

    public void onDragEventHandler(){

        dragPane.setPrefWidth(dragPane.getScene().getWidth());
        dragPane.setPrefHeight(dragPane.getScene().getHeight());

        dragPane.setDisable(true);
        dragPane.setOpacity(0.9);
    }

    public void onDragEventExitHandler(){

        dragPane.setDisable(false);
        dragPane.setOpacity(0.0);
    }
}