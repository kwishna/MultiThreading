package jenkins;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			FileUtils.write(new File(SystemUtils.getUserDir()+"/read.txt"), "Rough", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
