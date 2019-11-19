package ThreadLocals;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriverService.Builder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class DriverServ {

	public static void main(String[] args) throws IOException {

		var op = new ChromeOptions()
//				.addArguments("--headless")
				.addArguments("--incognito")
				.setAcceptInsecureCerts(true)
				.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
//				.setPageLoadStrategy(PageLoadStrategy.EAGER); // NOT YET SUPPORTED ERROR
		
		DesiredCapabilities cap = new DesiredCapabilities(op);
		cap.setPlatform(Platform.WIN10);
		
		Builder builder = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("E:\\chromedriver.exe"))
				.usingAnyFreePort()
//				.usingPort(8080)
				.withLogFile(new File("./logger.log"))
				.withVerbose(true);
		
		DriverService service = builder.build();
		
		System.out.println(":: Does The Driver Supports Caps -> "+builder.score(cap));		
		
		if(!service.isRunning()) {
			service.start();
		}
		
		WebDriver driver = new RemoteWebDriver(service.getUrl(), op);
		driver.get("https://www.google.co.in");
		driver.findElement(By.name("q")).sendKeys("US-Iran War 2019");
		driver.findElement(By.name("btnK")).submit();
		List<WebElement> resultSize = driver.findElements(By.className("r"));
		
		System.out.println(":: Result Count Is "+resultSize.stream().filter(a -> a.getAttribute("class").equalsIgnoreCase("r")).count());		
		
		driver.quit();
		
		if(service.isRunning()) {
			
			service.stop();
		}
		
	}
}
