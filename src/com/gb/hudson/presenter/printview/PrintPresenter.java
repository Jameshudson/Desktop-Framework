package com.gb.hudson.presenter.printview;

import java.io.File;
import java.net.URL;
import java.nio.file.attribute.FileTime;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.PrinterService;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

public class PrintPresenter implements Initializable{

	private final ObservableList<FileInfo> data =
			FXCollections.observableArrayList();
	
	@FXML
	TableView table;
	
	@Inject
	Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn fileOrderCol = getTableColumnByName(table, "Print order");

		//TableColumn fileNameCol = new TableColumn("file Name");
		fileOrderCol.setCellValueFactory(
				new PropertyValueFactory<>("filePlace"));
		fileOrderCol.setResizable(true);

		TableColumn fileNameCol = getTableColumnByName(table, "File Name");

		//TableColumn fileNameCol = new TableColumn("file Name");
		fileNameCol.setCellValueFactory(
				new PropertyValueFactory<>("fileName"));
		fileNameCol.setResizable(true);

		TableColumn fileDateCol = getTableColumnByName(table, "Date Created");
		fileDateCol.setResizable(true);

		//TableColumn fileDateCol = new TableColumn("file date");
		fileDateCol.setCellValueFactory(
				new PropertyValueFactory<>("fileDate"));

		TableColumn printStatusCol = getTableColumnByName(table, "Print status");
		printStatusCol.setResizable(true);

		//TableColumn printStatusCol = new TableColumn("print status");
		printStatusCol.setCellValueFactory(
				new PropertyValueFactory<>("printStatus"));

//		table.getColumns().addAll(fileNameCol, fileDateCol, printStatusCol);
		table.setItems(data);

//		model.AddListener(PrinterService.PRINTERS_FOUND, (data) -> {
//
//
//			return true;
//		});

		model.AddListener(PrinterService.LOADED_FILE, (data) ->{

			if(data.containsKey("file name") && data.containsKey("file date") && data.containsKey("file place")){

				FileTime time = (FileTime) data.get("file date");

				ObservableList<FileInfo> tableData = table.getItems();
				tableData.add(new FileInfo((int)data.get("file place"), (String)data.get("file name"), time.toString(), "Pending"));
			}

			return true;
		});

		PrinterService printer = (PrinterService)model.getService("PrintSerivce");

		printer.listPrinterNames();
	}

	private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
		for (TableColumn<T, ?> col : tableView.getColumns())
			if (col.getText().equals(name)) return col ;
		return null ;
	}

	public class FileInfo{

		private final SimpleIntegerProperty filePlace;

		private final SimpleStringProperty fileName;
		private final SimpleStringProperty fileDate;
		private final SimpleStringProperty printStatus;

		public FileInfo(int filePlace, String fileName, String fileDate, String printStatus){

			this.filePlace = new SimpleIntegerProperty(filePlace);
			this.fileName = new SimpleStringProperty(fileName);
			this.fileDate = new SimpleStringProperty(fileDate);
			this.printStatus = new SimpleStringProperty(printStatus);
		}

		public String getFileName() {
			return fileName.get();
		}

		public SimpleStringProperty fileNameProperty() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName.set(fileName);
		}

		public String getPrintStatus() {
			return printStatus.get();
		}

		public SimpleStringProperty printStatusProperty() {
			return printStatus;
		}

		public void setPrintStatus(String printStatus) {
			this.printStatus.set(printStatus);
		}

		public String getFileDate() {
			return fileDate.get();
		}

		public SimpleStringProperty fileDateProperty() {
			return fileDate;
		}

		public void setFileDate(String fileDate) {
			this.fileDate.set(fileDate);
		}

		public int getFilePlace() {
			return filePlace.get();
		}

		public SimpleIntegerProperty filePlaceProperty() {
			return filePlace;
		}

		public void setFilePlace(int filePlace) {
			this.filePlace.set(filePlace);
		}
	}
}
