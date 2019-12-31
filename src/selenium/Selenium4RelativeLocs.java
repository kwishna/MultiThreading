package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium4RelativeLocs {

	private static final By fbLastName = By.cssSelector(".inputtext[name='lastname']");
	private static final By fbFirstname = By.cssSelector(".inputtext[name='firstname']");

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("https://www.facebook.com");
		Thread.sleep(5000);
//		driver.findElement(RelativeLocator.withTagName("input").toLeftOf(fbLastName)).sendKeys("HelloWorld");

	}
}
