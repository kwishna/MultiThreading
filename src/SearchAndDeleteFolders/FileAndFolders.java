package SearchAndDeleteFolders;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.forceDelete;

public class FileAndFolders {

	private static final String workSpace = "F:\\Personal\\Locker\\evernote backup\\Study_Documents\\PDF\\E-Books";
	private static final Long twoMonthsBefore = Long.valueOf(LocalDate.now().minusMonths(2).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	private static String regex = "";


	public static void main(String[] args) {

		// printAllPDFFileInsideFolder(workSpace, twoMonthsBefore);
		// deleteAllFoldersForMatchingRegex(workSpace, regex);

		// To Print Files With Matching Extensions.
		List l = (List) FileUtils.listFiles(new File(workSpace), new String[]{"pdf", "txt", "epub"}, true);
		System.out.println(l.size());
	}

	public void printAllPDFFileInsideFolder(String workSpace, Long thresholdDate){

		// To Find PDF Files
		FileUtils.listFiles(new File(workSpace), new WildcardFileFilter("*.pdf"), TrueFileFilter.INSTANCE)
				.parallelStream().filter(f -> f.lastModified() < thresholdDate).collect(Collectors.toList())
				.forEach(System.out::println);
	}

	public void deleteAllFoldersForMatchingRegex(String workSpace, String regex){

		// Delete All Folders Inside Workspace With Matching Regex.
		FileUtils.listFilesAndDirs(new File(workSpace), new WildcardFileFilter("*"), TrueFileFilter.INSTANCE) // workspace param
				.parallelStream()
				.filter(a -> (a.isDirectory() && a.getName().matches(regex))) // regex param
				.forEach(b -> Arrays.stream(Objects.requireNonNull(b.listFiles(x -> (getDateOnly(x.getName()) < Long.parseLong(LocalDate.now().minusMonths(2).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
				))))
						.forEach(c -> {
							try {
								FileDeleteStrategy.FORCE.delete(c);
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Deleted :: " + c.getAbsolutePath());
						}));

	}

	// Below Code Is For AWS Machine Folder Deletion.

	private static Long getDateOnly(String fileName) {

		String onlyDigitPartOfFileName = fileName.replaceAll("[^0-9]", "");

		if (onlyDigitPartOfFileName.length() > 7) return Long.parseLong(onlyDigitPartOfFileName.substring(0, 8));

		else return 99999999L;
	}

	private static List<File> getMeAllFolders(File f) {

//		return Arrays.asList(f.listFiles((f1) -> (f1.isDirectory() && f1.getName().matches(regex))));

		return Arrays.stream(Objects.requireNonNull(f.list()))

				.map(a -> new File(f.getAbsolutePath() + "\\" + a))

				.filter(File::isDirectory)

				.filter(d -> d.getName().matches(regex))

//				.filter(directory -> !directory.getName().startsWith(".")) // To Avoid Hidden Directory.
//
//				.filter(directory -> !directory.getName().endsWith("."))

				.filter(dir -> dir.getName().length() > 7).collect(Collectors.toList());
	}


	private static List<File> getMeTEST_RESULTSFolders(File f) {

		return Arrays.stream(Objects.requireNonNull(f.list()))

				.map(a -> new File(f.getAbsolutePath() + "\\" + a))

				.filter(File::isDirectory)

				.filter(c -> c.getAbsolutePath().toLowerCase().contains("test_results"))

				.collect(Collectors.toList());
	}

	private static List<File> getMeTEST_RESULTSFoldersToDelete(List<File> f, Long belowThisDate) {

//		return f.stream().filter(f2 -> (f2.lastModified() < belowThisDate)).collect(Collectors.toList());

		return f.stream()

				.filter(File::isDirectory)

				.filter(d -> getDateOnly(d.getName()) < belowThisDate)

				.collect(Collectors.toList());

	}

	private static void findAndDeleteFiles(File f, Long date) {

		if (f.exists() && f.isDirectory()) {

			getMeAllFolders(f).forEach(f1 -> findAndDeleteFiles(f1, date));

			List<File> allTestResultFolders = getMeTEST_RESULTSFolders(f);

			allTestResultFolders

					.forEach(a -> getMeTEST_RESULTSFoldersToDelete(allTestResultFolders, date)

							.stream()

							.filter(File::exists)

							.forEach(n -> {

								try {

									forceDelete(n);
//									FileDeleteStrategy.FORCE.delete(n);	// Force Delete!

									System.out.println("Deleted -- " + n.getAbsolutePath());

								} catch (IOException e) {

									System.out.println("Not Deleted -- " + e.getMessage());

								}

							}));

		}

		System.out.println("File Doesn't Exist Or Already Deleted! - " + f.getAbsolutePath());

	}
}
