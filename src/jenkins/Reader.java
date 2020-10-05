package jenkins;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;

public class Reader {
	public static void main(String[] args) {
		try {
			String fileName = FileUtils.readFileToString(new File(SystemUtils.getUserDir()+"/read.txt"), "UTF-8");
			String cmd = "@ECHO OFF\ncd %~dp0\nmvn exec:java -Dexec.mainClass=\"jenkins."+fileName+"\"\n"+
					"echo ---- COPY FOLDERS ----\n" +
					"cd %projectDir%\n" +
					"IF NOT EXIST TEST_RESULTS MD TEST_RESULTS\n" +
					"xcopy /E \"%projectDir%/target/\" \"TEST_RESULTS/MultipleConcepts/PipeLine/%datetime%\"\n" +
					"echo Done\n";
			FileUtils.write(new File(SystemUtils.getUserDir()+"/rerun.bat"), cmd, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
