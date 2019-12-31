package CsvReading;

import io.appium.java_client.windows.WindowsElement;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;

public class WinAppDriverTest {

	private static WindowsDriver<WindowsElement> winAppSession = null;
	private static WebElement winAppResult = null;

	@BeforeClass
	public static void setup() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("app", "Microsoft.WindowswinApp_10.1910.0.0_x64__8wekyb3d8bbwe");
			winAppSession = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), capabilities);
			winAppSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			winAppResult = winAppSession.findElementByAccessibilityId("winAppResults");
			Assert.assertNotNull(winAppResult);

		}catch(Exception e){
			e.printStackTrace();
		} finally {
		}
	}

	@Before
	public void Clear()
	{
		winAppSession.findElementByName("Clear").click();
		Assert.assertEquals("0", _GetwinAppResultText());
	}

	@AfterClass
	public static void TearDown()
	{
		winAppResult = null;
		if (winAppSession != null) {
			winAppSession.quit();
		}
		winAppSession = null;
	}

	@Test
	public void Addition()
	{
		winAppSession.findElementByName("One").click();
		winAppSession.findElementByName("Plus").click();
		winAppSession.findElementByName("Seven").click();
		winAppSession.findElementByName("Equals").click();
		Assert.assertEquals("8", _GetwinAppResultText());
	}

/*	@Test
	public void Combination()
	{
		winAppSession.findElementByName("Seven").click();
		winAppSession.findElementByName("Multiply by").click();
		winAppSession.findElementByName("Nine").click();
		winAppSession.findElementByName("Plus").click();
		winAppSession.findElementByName("One").click();
		winAppSession.findElementByName("Equals").click();
		winAppSession.findElementByName("Divide by").click();
		winAppSession.findElementByName("Eight").click();
		winAppSession.findElementByName("Equals").click();
		Assert.assertEquals("8", _GetwinAppResultText());
	}

	@Test
	public void Division()
	{
		winAppSession.findElementByName("Eight").click();
		winAppSession.findElementByName("Eight").click();
		winAppSession.findElementByName("Divide by").click();
		winAppSession.findElementByName("One").click();
		winAppSession.findElementByName("One").click();
		winAppSession.findElementByName("Equals").click();
		Assert.assertEquals("8", _GetwinAppResultText());
	}

	@Test
	public void Multiplication()
	{
		winAppSession.findElementByName("Nine").click();
		winAppSession.findElementByName("Multiply by").click();
		winAppSession.findElementByName("Nine").click();
		winAppSession.findElementByName("Equals").click();
		Assert.assertEquals("81", _GetwinAppResultText());
	}

	@Test
	public void Subtraction()
	{
		winAppSession.findElementByName("Nine").click();
		winAppSession.findElementByName("Minus").click();
		winAppSession.findElementByName("One").click();
		winAppSession.findElementByName("Equals").click();
		Assert.assertEquals("8", _GetwinAppResultText());
	}*/

	protected String _GetwinAppResultText()
	{
		// trim extra text and whitespace off of the display value
		return winAppResult.getText().replace("Display is", "").trim();
	}

}
