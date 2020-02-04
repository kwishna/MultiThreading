package SearchAndDeleteFolders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;

import org.apache.http.HttpEntity;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.message.BasicHeader;

import org.apache.http.util.EntityUtils;

import org.apache.poi.ss.usermodel.DateUtil;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;

import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class PropertyJavaMismtach {

	/**

	 * Code For Validation Whether The Locators Name Used In Java File Is Present In

	 * Properties File Or Not?

	 *

	 * 1) Enter Your Java Page File Path.

	 * 2) Enter Corresponding Properties File Path.

	 */


		private static Set<String> getMeAllLocatorsUsedInPage(String pathOfFile) throws IOException {

			Set<String> set = new HashSet<>();

			String regexToFilterAllGetProperty = "getProperty\\([a-zA-Z\"]*\\)";

			String regexToFilterAllLocatorsName = "\"[a-zA-Z]*\"";

			String dataFromPage = ""; //Files.readString(Paths.get(pathOfFile), StandardCharsets.UTF_8);

			Matcher match = Pattern.compile(regexToFilterAllGetProperty).matcher(dataFromPage);

			while (match.find()) {

				Matcher match1 = Pattern.compile(regexToFilterAllLocatorsName).matcher(match.group());

				while (match1.find()) set.add(match1.group().replaceAll("\"", ""));

			}

			System.out.println(set);

			return set;

		}

		private static void printIfNotFoundInProperties(String pathOfPropertyFile, Set<String> keys) throws IOException {

			Properties prop = new Properties();

			Path file = Paths.get(pathOfPropertyFile);

			prop.load(Files.newInputStream(file));

			keys.forEach(a -> {

				System.out.print(prop.containsKey(a) ? "" : a + " <-- Not Found In " + file.getFileName() + "\n");

			});

		}



		public static void main(String[] args) throws IOException {

			String propertyFile = System.getProperty("user.dir")+"\\src\\main\\resources\\MobPage.properties";

			String javaPage = System.getProperty("user.dir")+"\\src\\main\\java\\com\\MobPage.java";

			printIfNotFoundInProperties(propertyFile, getMeAllLocatorsUsedInPage(javaPage));

		}

}
