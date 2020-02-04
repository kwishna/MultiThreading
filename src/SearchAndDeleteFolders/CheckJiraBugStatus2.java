package SearchAndDeleteFolders;

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

/**

 *         <groupId>org.apache.httpcomponents</groupId>

 *             <artifactId>httpclient</artifactId>

 *             <version>4.5.2</version>

 *         </dependency>

 *

 *         <dependency>

 *             <groupId>net.serenity-bdd</groupId>

 *             <artifactId>serenity-rest-assured</artifactId>

 *             <version>2.0.52</version>

 *         </dependency>

 *

 *   @apiNote Column 5 & 13 In Excel Sheet Will Only Be Fetched For Filtering Jira Bug IDs.

 *

 *   Use Your Kannoah Encoded String.

 *

 */

public class CheckJiraBugStatus2 {

	private static String encodedString = "encodedString";

	private static String baseUrl = "https://company.jira.com";

	private static void getStatusForThisBugId(String bug) {

		String bugID = bug;

		try {

			String responseBody = HttpClientBuilder.create().setDefaultHeaders(new ArrayList<Header>() {

				{

					add(new BasicHeader("Authorization", "Basic "+encodedString));

				}

			})

					.build()

					.execute(new HttpGet(baseUrl + "/jira/rest/agile/1.0/issue/" + bugID),

							httpResponse -> {

								HttpEntity entity = httpResponse.getEntity();

								return entity != null ? EntityUtils.toString(entity) : null;

							});

			String bugStatus = new io.restassured.path.json.JsonPath(responseBody).getString("fields.status.name");

			if (bugStatus.equalsIgnoreCase("Closed")) System.err.println(bugID + " <-- FAILED!!! Status Is : " + bugStatus);

			else System.out.println(bugID + " <-- Success. Status Is : " + bugStatus);

		} catch (IOException e) {

			System.out.println("Exception While Executing API " + e.getMessage());

		}

	}

	private static void getAllBugDetailsFromExcel(String filePath, String sheetName) throws Exception {

		Sheet sheet = WorkbookFactory.create(Files.newInputStream(Paths.get(filePath), StandardOpenOption.READ)).getSheet(sheetName);

		Row.MissingCellPolicy policy = Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

		StringBuilder builder = new StringBuilder();

		for (int i=0; i<sheet.getPhysicalNumberOfRows(); i++){

			Row r = sheet.getRow(i);

			builder.append(getCellValue(r.getCell(5, policy)).trim());

			builder.append(getCellValue(r.getCell(13, policy)).trim());

		}

		getSetOfId(builder);

	}

	private static void getSetOfId(StringBuilder str) {

		String regexForId = "([0-9]{5})";

		Matcher match = Pattern.compile(regexForId).matcher(str);

		while (match.find()) getStatusForThisBugId(match.group().trim());

	}

	private static String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {

		if (cell == null) throw new RuntimeException("[Can'T Fetch Value From null Cell]");

		String output = "";

		switch (cell.getCellTypeEnum()) {

			case STRING:

				output = output + cell.getRichStringCellValue().getString();

				break;

			case NUMERIC:

				if (DateUtil.isCellDateFormatted(cell)) {

					output = output + cell.getDateCellValue();

				} else {

					output = output + cell.getNumericCellValue();

				}

				break;

			case FORMULA:

				output = output + cell.getCellFormula();

				break;

			case BOOLEAN:

				output = output + cell.getBooleanCellValue();

				break;

			case ERROR:

				output = output + cell.getErrorCellValue();

				break;

			default:

				break;

		}

		return output;

	}

	public static void main(String[] args) throws Exception {

		String filePath = System.getProperty("user.dir")+"\\file.xlsx";

		String sheetName = "Regression";

		getAllBugDetailsFromExcel(filePath, sheetName);

	}

}