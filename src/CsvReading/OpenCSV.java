package CsvReading;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVParserWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Important Classes :-
 * {@link CSVReader}
 * {@link com.opencsv.CSVParser}
 * {@link com.opencsv.CSVWriter}
 * {@link CSVParserWriter}
 * {@link CSVParserBuilder}
 *
 */
public class OpenCSV {

	public static final String fileLocation = System.getProperty("user.dir")+"/src/CsvReading/csvFile.csv";

	public static void main(String[] args) throws IOException, CsvException {

		Reader r = Files.newBufferedReader(Path.of(fileLocation));
		CSVReader reader = new CSVReader(r);

		// 1st Way
		List<String[]> csvData = reader.readAll();
		csvData.forEach(a -> System.out.println(Arrays.toString(a)));

		// 2nd Way
		Iterator<String[]> itr = reader.iterator();
		while (itr.hasNext()){
			String arr = Arrays.toString(itr.next());
			System.out.println(arr);
		}

		reader.close();
		r.close();

		CSVParser parser = new CSVParserBuilder().withIgnoreLeadingWhiteSpace(true).build();
		CSVParserWriter writer = new CSVParserWriter(Files.newBufferedWriter(Path.of(fileLocation), StandardOpenOption.APPEND), parser, "\n");

		String[] str1 = new String[]{"Messi", "1", "Argentina"};
		String[] str2 = new String[]{"Pele", "2", "Brazil"};
		String[] str3 = new String[]{"Ronaldo", "3", "Portugal"};

		writer.writeAll(List.of(str1, str2, str3));
		writer.flush();
	}
}
