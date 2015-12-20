package com.gb.hudson.printer;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;

import org.apache.pdfbox.printing.PDFPageable;

public class PDFPrinter{
	
	private String printerName = "";
	
	private PrinterJob job = PrinterJob.getPrinterJob();
	private PrintService printService;
	
	private List<PDFPageable> pdfPrintList;
	
	public PDFPrinter(){
		
		pdfPrintList = new ArrayList<PDFPageable>();
	}
	
	public void addPdfToPrintList(PDFPageable pdf){
		
		this.pdfPrintList.add(pdf);
	}
	
	public void printPDF(PrintService printer) throws NoPrinterSelectedException, PrinterException {
		
		if(printer != null){
			
			for(int i = pdfPrintList.size() - 1; i > 0; i--){
				
				this.job.setPageable(pdfPrintList.get(i));
				this.job.print();
			}
		}else{

			System.out.println(this.printerName);
			
			if(!this.printerName.equals("")){
				
				this.printService = findPrintService(printerName);
				this.printPDF(printService);
			}else{
				
				throw new NoPrinterSelectedException("No printer selected.");
			}
		}
	}
	
	public void printPDF() throws NoPrinterSelectedException, PrinterException {
		
		if(!this.printerName.equals("")){

			this.printService = findPrintService(printerName);
			
			printPDF(this.printService);
		}else{
			 throw new NoPrinterSelectedException("No printer selected.");
		}
	}
	
	public void selectPrinter(String printerName){
		this.printerName = printerName;
	}
	
	//static methods
	public static PrintService findPrintService(String printerName) {

        printerName = printerName.toLowerCase();

        PrintService service = null;

        // Get array of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();

        // Retrieve a print service from the array
        for (int index = 0; service == null && index < services.length; index++) {

            if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
                service = services[index];
            }
        }

        // Return the print service
        return service;
    }

    /**
     * Retrieves a List of Printer Service Names.
     * 
     * @return List
     */
    public static List<String> getPrinterServiceNameList() {

		// get list of all print services
		PrintService[] services = PrinterJob.lookupPrintServices();
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < services.length; i++) {
			list.add(services[i].getName());
		}

		return list;
	}

	public class NoPrinterSelectedException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    	
    	public NoPrinterSelectedException(String messsage){
    		super(messsage);
    	}
    }

}