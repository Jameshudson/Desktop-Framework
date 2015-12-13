//package test.model.models.test;
//
//import Model;
//import com.gb.hudson.model.*;
//import org.junit.*;
//
//import javax.print.PrintService;
//import javax.print.PrintServiceLookup;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PrinterServiceTest {
//
//	@Test
//	public void printerFoundTest() {
//
//		Model model = new Model();
//		model.addService("PrinterService", new PrinterService(model));
//
//		boolean hookRun = false;
//
//		model.AddListener("PRINTERS_FOUND", (data) -> {
//
//			List<String> names = (ArrayList<String>)data.get("printers");
//
//			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//
//			System.out.println(names.size());
//
//			if(printServices.length > names.size() || printServices.length < names.size()){
//
//				fail();
//				return false;
//			}
//
//			return false;
//		});
//
//		((PrinterService)model.getService("PrinterService")).listPrinterNames();
//	}
//
//	@Test
//	public void printerNotFoundTest(){
//
//		Model model = new Model();
//		model.addService("PrinterService", new PrinterService(model));
//
//		model.AddListener("NO_PRINTERS_FOUND", (data) -> {
//
//			//testing that the return value from the hook is null.
//			if(data != null){
//
//				fail();
//			}
//
//			return false;
//		});
//
//		((PrinterService)model.getService("PrinterService")).listPrinterNames();
//	}
//
//	@Test
//	public void selectPrinterTest(){
//
//		Model model = new Model();
//		model.addService("PrinterService", new PrinterService(model));
//
//		model.AddListener("PRINTER_SELECTED", (data) -> {
//
//			String printerName = (String)data.get("printer_name");
//
//			assertEquals( "james", printerName);
//
//			return false;
//		});
//
//		((PrinterService)model.getService("PrinterService")).selectPrinter("james");
//	}
//}