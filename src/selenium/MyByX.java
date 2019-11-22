package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.io.Serializable;
import java.util.List;

public class MyByX extends By implements Serializable {

	private final String x;

	public MyByX(String x) {
		if (x == null) {
			throw new IllegalArgumentException("Cannot find elements when the id is null.");
		}

		this.x = x;
	}

	@Override
	public List<WebElement> findElements(SearchContext context) {

			// TODO Implement Custom FindBy. Once New w3c Implementaion Comes Up In Selenium 4. Bqz, Current Is Deprecated!
		return null;
	}
}
