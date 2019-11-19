package ReadPDF;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PDFReads {

	public void printPDF(String filePath, int startPage, int endPage) throws InvalidPasswordException, IOException {
		
		File f = new File(System.getProperty("user.home")+"\\Desktop\\New folder\\PDF\\learn python 3 the hard way ( PDFDrive.com ).pdf");
		PDDocument docs = PDDocument.load(f);
		
		PDFTextStripper pTxt = new PDFTextStripper();
		pTxt.setStartPage(5);
		pTxt.setEndPage(6);
		
		System.out.println(pTxt.getText(docs));
	}
	
	public static void main(String[] args) throws InvalidPasswordException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.goole.co.in");
		driver.close();

	}

}
