package Common;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeNew {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.co.in");
		driver.get("https://www.google.co.fr");
		driver.navigate().to("https://www.google.co.pt");
		driver.navigate().to("https://www.google.co.uk");
		driver.navigate().to("https://www.google.co.it");

//		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.yahoo.co.in");
		driver.navigate().to("https://www.yandex.com");
		driver.navigate().to("https://www.yahoo.co.in");
	}
}
