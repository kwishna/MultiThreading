package selenium;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Download And Read PDF File Using Selenium. ReadPDF
 */
public class SeleniumPDF {

	public static void downloadAFile() throws IOException, InterruptedException {

		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
		chromePrefs.put("plugins.always_open_pdf_externally", true);

		ChromeOptions ops = new ChromeOptions();
		ops.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		ops.setAcceptInsecureCerts(true);
		ops.setExperimentalOption("prefs", chromePrefs);

		ChromeDriverService s = new ChromeDriverService.Builder()
//				.withVerbose(true)
				.withLogFile(new File("./driverLog.log"))
				.usingPort(5555)
				.usingDriverExecutable(new File("E:\\chromedriver.exe")).build();
		s.start();

		WebDriver driver;

		driver = new RemoteWebDriver(s.getUrl(), ops);

		driver.get("https://file-examples.com/index.php/sample-documents-download/sample-pdf-download/");
		driver.findElement(By.cssSelector(".btn-orange[href*='150k']")).click();

		Thread.sleep(1000);
		driver.close();
		s.stop();
	}

	public static void main(String[] args) throws IOException{

		List<File> f = (List<File>) FileUtils.listFiles(new File(System.getProperty("user.dir")), new String[]{"pdf"}, true);
		f.sort(Comparator.comparing(File::lastModified));
		File pdf = f.get(0);
		org.apache.pdfbox.pdmodel.PDDocument doc = org.apache.pdfbox.pdmodel.PDDocument.load(pdf);
		int pageCount = doc.getNumberOfPages();
		org.apache.pdfbox.text.PDFTextStripper pTxt;
		String toFind = "Ut facilisis et lacus eu cursus.".toLowerCase();
		for (int i = 0; i < pageCount; i++) {
			pTxt = new org.apache.pdfbox.text.PDFTextStripper();
			pTxt.setStartPage(i);
			pTxt.setEndPage(i + 1);
			String str = pTxt.getText(doc);
			System.out.println(str);
			if (str.toLowerCase().contains(toFind)) {
				System.err.println("Found In : " + i);
				break;
			} else System.err.println("Not Found");
		}

		PDPage p = doc.getPage(0);
		String extractedText = new PDFTextStripper().getText(doc);
	}
}
