package test.com.gb.hudson.utill;


import com.gb.hudson.utill.PDFPrinter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

public class PDFPrinterTest {

	@Test
	public void addPdfToPrintListTest() {
		PDFPrinter pdfPrinter = new PDFPrinter();

        PDDocument pdf = new PDDocument();
        PDFPageable printable = new PDFPageable(pdf);

        int place1 = pdfPrinter.addPdfToPrintList(printable);
        int place2 = pdfPrinter.addPdfToPrintList(printable);
        int place3 = pdfPrinter.addPdfToPrintList(printable);

        assertEquals(0, place1);
        assertEquals(1, place2);
        assertEquals(2, place3);
	}

    @Test
    public void movePdfTest(){

        PDFPrinter pdfPrinter = new PDFPrinter();

        PDDocument pdf = new PDDocument();
        PDFPageable printable = new PDFPageable(pdf);

        int place1 = pdfPrinter.addPdfToPrintList(printable);
        int place2 = pdfPrinter.addPdfToPrintList(printable);
        int place3 = pdfPrinter.addPdfToPrintList(printable);

        boolean failed = pdfPrinter.movePdf(2, printable);

        if(failed == false){
            fail();
        }
    }
//    TODO
    @Test
    public void printPDFTest(){

        PDFPrinter pdfPrinter = new PDFPrinter();

        PDDocument pdf = new PDDocument();
        PDFPageable printable = new PDFPageable(pdf);

        int place1 = pdfPrinter.addPdfToPrintList(printable);
        int place2 = pdfPrinter.addPdfToPrintList(printable);
        int place3 = pdfPrinter.addPdfToPrintList(printable);


    }

//    TODO
    public void selectPrinterTest(){

    }

//    TODO
    public void findPrintServiceTest(){

    }
//    TODO
    public void getPrinterServiceNameList(){

    }
}
