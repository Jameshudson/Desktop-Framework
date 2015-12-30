package test.com.gb.hudson.model.models;

import com.gb.hudson.model.*;
import com.gb.hudson.model.models.PrinterService;
import org.junit.Test;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


public class PrinterServiceTest {

	@Test
	public void printerFoundTest() {

		Model model = new Model();
		model.addService("PrinterService", new PrinterService(model));

		boolean hookRun = false;

		model.AddListener("PRINTERS_FOUND", (data) -> {

			List<String> names = (ArrayList<String>)data.get("printers");

			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

			if(printServices.length > names.size() || printServices.length < names.size()){

				fail();
				return false;
			}

			return false;
		});

		((PrinterService)model.getService("PrinterService")).listPrinterNames();
	}

	@Test
	public void printerNotFoundTest(){

		Model model = new Model();
		model.addService("PrinterService", new PrinterService(model));

		model.AddListener("NO_PRINTERS_FOUND", (data) -> {

			//testing that the return value from the hook is null.
			if(data != null){

				fail();
			}

			return false;
		});

		((PrinterService)model.getService("PrinterService")).listPrinterNames();
	}

	@Test
	public void selectPrinterTest(){

		Model model = new Model();
		model.addService("PrinterService", new PrinterService(model));

		model.AddListener("PRINTER_SELECTED", (data) -> {

			String printerName = (String)data.get("printer_name");

			System.out.println(printerName);

			assertEquals( "PDFwriter", printerName);

			return false;
		});

		((PrinterService)model.getService("PrinterService")).selectPrinter("PDFwriter");
	}
}