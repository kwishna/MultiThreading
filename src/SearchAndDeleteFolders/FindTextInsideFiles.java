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

	private static final String workSpace = "F:\\ABD\\Codes";

	public static void main(String[] args) {

		findTextInFileTypesInWorkSpace(workSpace, "krishna","*.java");
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

	public static void findTextInThisWorkSpace(String workSpace, String textToFind) {

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
}
