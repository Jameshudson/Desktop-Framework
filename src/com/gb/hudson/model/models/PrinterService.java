package com.gb.hudson.model.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.components.Service;
import com.gb.hudson.printer.PDFPrinter;
import com.gb.hudson.printer.PDFPrinter.NoPrinterSelectedException;

public class PrinterService implements Service {
	
	public static final String PRINTER_SERVICE_NAME = "PrintSerivce";
	
	//printer error hooks
	public static final String ERROR_NO_PRINTER = "ERROR_NO_PRINTER";
	public static final String ERROR_PRINT_JOB = "ERROR_PRINT_JOB";
	
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
			
			model.fireListenerEvent(NO_PRINTER_SELECTED, null);
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
		
		data.put("printer_name", printerName);
		model.fireListenerEvent("PRINTER_SELECTED", data);
	}

	@Override
	public void removing() {
		// TODO Auto-generated method stub

	}

}