package SearchAndDeleteFolders;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * To Find Text In Any File And Any Folder.
 * @author AB D
 */
public class FindTextInsideFiles {

	private static final String workSpace = "F:\\ABD\\GitHub";

	public static void main(String[] args) {

//		findTextInFileTypesInWorkSpace(workSpace, "krishna","*.java");
		findTextInThisWorkSpace(workSpace, "jbehave");
	}

	private static void findTextInFileTypesInWorkSpace(String workSpace, String textToFind, String... wildCardChar){

		FileUtils.listFiles(new File(workSpace), new WildcardFileFilter(wildCardChar), TrueFileFilter.INSTANCE)
				.parallelStream().filter(a -> {
			String allDate = "";
			try {
				allDate = new String(Files.readAllBytes(a.toPath()));
			} catch (IOException e) {
				System.err.println("Error Here : " + a.getAbsolutePath());
			}
			return allDate.toLowerCase().contains(textToFind.toLowerCase());
		}).forEach(x -> System.out.println(x.getAbsolutePath()));
	}

	private static void findTextInThisWorkSpace(String workSpace, String textToFind) {

		List<File> l = (List<File>) FileUtils.listFiles(new File(workSpace), new String[]{"java", "properties"}, true);
		l.parallelStream().filter(a -> {
			String allDate = "";
			try {
				allDate = Files.readString(a.toPath());
			} catch (IOException e) {
				System.err.println("Error Here : " + a.getAbsolutePath());
			}
			return allDate.toLowerCase().contains(textToFind.toLowerCase());
		}).forEach(x -> System.out.println(x.getAbsolutePath()));
	}

	private static void findTextInThisWorkSpaceUsingRegex(String workSpace, String regex, String... fileExtensions) {

		if(fileExtensions.length == 0) fileExtensions = new String[]{"java", "properties"};

		List<File> l = (List<File>) FileUtils.listFiles(new File(workSpace), fileExtensions, true);
		l.parallelStream().filter(a -> {
			String allData = "";
			try {
				allData = Files.readString(a.toPath());
			} catch (IOException e) {
				System.err.println("Error Here : " + a.getAbsolutePath());
			}
			return allData.toLowerCase().matches(regex);
		}).forEach(x -> System.out.println(x.getAbsolutePath()));
	}
}
