package com.gb.hudson.model.models;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.components.Service;
import com.gb.hudson.utill.PDFPrinter;
import com.gb.hudson.utill.PDFPrinter.NoPrinterSelectedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PrinterService implements Service {
	
	public static final String PRINTER_SERVICE_NAME = "PrintSerivce";
	
	//printer error hooks
	public static final String ERROR_NO_PRINTER = "ERROR_NO_PRINTER";
	public static final String ERROR_PRINT_JOB = "ERROR_PRINT_JOB";

	public static final String ERROR_LOADING_FILE = "ERROR_LOADING_FILE";
	public static final String LOADED_FILE = "LOADED_FILE";
	
	//new printer's found hooks
	public static final String PRINTERS_FOUND = "PRINTERS_FOUND";
	public static final String NO_PRINTERS_FOUND = "NO_PRINTERS_FOUND";
	
	//printer selected
	public static final String PRINTER_SELECTED = "PRINTER_SELECTED";
	
	public static final String NO_PRINTER_SELECTED = "NO_PRINTER_SELECTED";
	
	//print job completed
	public static final String PRINT_JOB_COMPLETE = "PRINT_JOB_COMPLETE";
	
	private Model model;
	
	private PDFPrinter pdfPrinter;

	public PrinterService(Model model) {
		
		this.model = model;
		pdfPrinter = new PDFPrinter();
	}

	@Override
	public void init() {
		
		listPrinterNames();
	}
	
	public void startPrintJob(){
		
		try {
			pdfPrinter.printPDF();
		} catch (NoPrinterSelectedException e) {

			e.printStackTrace();
			model.fireListenerEvent(NO_PRINTER_SELECTED, null);
		} catch (PrinterException e) {

			model.fireListenerEvent(ERROR_PRINT_JOB, null);
		} catch (NullPointerException e) {

			e.printStackTrace();
			model.fireListenerEvent(ERROR_PRINT_JOB, null);
		}
	}

	public void addToPrintList(File file){

		if(file != null && file.exists()){

			try {
				PDDocument document = PDDocument.load(file);


				//converts the pdf to a printable formet and adds it to the print list.
				PDFPageable printable = new PDFPageable(document);
				int id = this.pdfPrinter.addPdfToPrintList(printable);

				BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

				HashMap<String,Object> data = new HashMap<String, Object>();
				data.put("file name", file.getName());
				data.put("file date", attr.creationTime());
				data.put("file place", id);

				this.model.fireListenerEvent(LOADED_FILE, data);
			} catch (IOException e) {
				HashMap<String,Object> data = new HashMap<String, Object>();

				data.put("file", file);
				this.model.fireListenerEvent(ERROR_LOADING_FILE, data);
			}
		}
	}
	
	/**
	 * listPrinterNames
	 * 
	 * <br />
	 * returns a list of printers found. the containing hook that is fired when this 
	 * methods founds a list of printer is PRINTERS_FOUND. If no printer where found then the NO_PRINTER_FOUND hook is fired.
	 * 
	 * @see PrinterService
	 * @return in the hook is a hashmap and the key for the printer that have been found is "printers".
	 * @since version 1.00
	 */
	public void listPrinterNames(){
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		ArrayList<String> printerNames = (ArrayList<String>) PDFPrinter.getPrinterServiceNameList();
		
		if(printerNames.isEmpty() || printerNames.size() == 0){
			
			model.fireListenerEvent(NO_PRINTERS_FOUND, null);
			return;
		}
		
		data.put("printers", printerNames);
		
		model.fireListenerEvent(PRINTERS_FOUND, data);
	}
	
	public void selectPrinter(String printerName){
		
		pdfPrinter.selectPrinter(printerName);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put(PRINTER_SELECTED, printerName);
		model.fireListenerEvent(PRINTER_SELECTED, data);
	}

	@Override
	public void removing() {
		// TODO Auto-generated method stub

	}

//	TODO
	public void insertCoverPages(boolean b) {
	}
//	TODO
	public void invertPages(boolean b) {
	}
}