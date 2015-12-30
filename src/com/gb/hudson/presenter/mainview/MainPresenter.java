package com.gb.hudson.presenter.mainview;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;
import com.gb.hudson.presenter.footerview.FooterView;
import com.gb.hudson.presenter.printview.PrintView;
import com.gb.hudson.presenter.tabview.TabView;

import com.gb.hudson.utill.FileUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.effect.*;

public class MainPresenter implements Initializable {

    private static final int BLUR_AMOUNT = 20;

    private static final GaussianBlur frostEffect =
            new GaussianBlur(20);

    @FXML
	AnchorPane left;

    @FXML
    BorderPane mainPane;

	@FXML
    AnchorPane right;

    @FXML
    AnchorPane dragPane;

    @FXML
    Label dragInfoLabel;

    @FXML
    AnchorPane footer;

	@Inject
	Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//        ResourceBundle lngBndl = ResourceBundle
//                .getBundle("com.gb.hduson.res.leng_en.LangBundle", new Locale("en", "EN"));


        //adding the print table
		PrintView printView = new PrintView();
		left.getChildren().add(printView.getView());
		
		TabView printerControls = new TabView();
		right.getChildren().add(printerControls.getView());

        FooterView footerView = new FooterView();
        footer.getChildren().add(footerView.getView());

		model.init();
	}

    public void onDragAction(){

        dragInfoLabel.setLayoutX((dragInfoLabel.getScene().getWidth()/2) - dragInfoLabel.getWidth());
        dragInfoLabel.setLayoutY((dragInfoLabel.getScene().getHeight()/2) - dragInfoLabel.getHeight());

        dragInfoLabel.setPrefWidth(dragInfoLabel.getScene().getWidth());
        dragInfoLabel.setPrefHeight(dragInfoLabel.getScene().getHeight());

        dragInfoLabel.setContentDisplay(ContentDisplay.CENTER);

        mainPane.setEffect(frostEffect);

        dragPane.setDisable(false);
        dragPane.setOpacity(1);
    }

    public void onDragExitAction(DragEvent event){

        Dragboard db = event.getDragboard();

        if(db.hasFiles()){

            int nFiles = db.getFiles().size();

            for(int i = nFiles - 1; i >=0; i--) {

                if(FileUtill.getFileExtension(db.getFiles().get(i)).equals("pdf")){

                    ((PrinterService)this.model.getService(PrinterService.PRINTER_SERVICE_NAME)).addToPrintList(db.getFiles().get(i));
                }
            }
        }

        mainPane.setEffect(null);

        dragPane.setDisable(true);
        dragPane.setOpacity(0.0);
    }
}