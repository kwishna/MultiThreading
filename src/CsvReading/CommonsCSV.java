package CsvReading;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Important Classes :-
 *
 * {@link CSVFormat}
 * {@link CSVParser}
 * {@link CSVPrinter}
 *
 */
public class CommonsCSV {

	public static final String fileLocation = System.getProperty("user.dir")+"/src/CsvReading/csvFile.csv";

	public static void main(String[] args) throws Exception {

		Reader r = Files.newBufferedReader(Path.of(fileLocation));
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("name","rollNo","location").parse(r);

//		CSVParser parser = new CSVParser(r, CSVFormat.EXCEL.withHeader("name","rollNo","location"));

		for (CSVRecord record : records) {

			System.out.println(record.toMap());
//			System.out.println(record);

			/*System.out.println(record.get("name"));
			System.out.println(record.get("rollNo"));
			System.out.println(record.get("location"));*/
		}


/*		CSVFormat format = CSVFormat.newFormat(',');
		CSVParser parser = format.parse(r);
		List<CSVRecord> recordList = parser.getRecords();
		recordList.forEach(a -> System.out.println(a.get("name")));*/

/*
		 public enum Headers {
			ID, CustomerNo, Name
		}

		Reader in = new FileReader("path/to/file.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
 */

/*
		Reader in = new FileReader("path/to/file.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
 */
		// Writing...
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileLocation));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withHeader("ID", "Name", "Designation", "Company")))
		{
			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");
			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));
			csvPrinter.flush();
		}

		// Reading...
		try ( Reader reader = Files.newBufferedReader(Paths.get(fileLocation));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withFirstRecordAsHeader() // .withHeader("Name", "Email", "Phone", "Country")
						.withIgnoreHeaderCase()
						.withTrim()))
		{
			for (CSVRecord csvRecord : csvParser) {
				// Accessing values by Header names
				String name = csvRecord.get("Name");
				String email = csvRecord.get("Email");
				String phone = csvRecord.get("Phone");
				String country = csvRecord.get("Country");
			}
		}


	}
}
