package Common;

import java.util.Arrays;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Apache Commons
 * 
 * @author AB D
 *
 */
public class CommonUtils {
	
	public static <T> void printArray(T[] array) {
		
		System.out.println("*************************************************");
		Arrays.stream(array).forEach(System.out::println);
		System.out.println("*************************************************");
	}

	public static void main(String[] args) {
		
		System.out.println("******** ArchUtils *********");		
		System.out.println(ArchUtils.getProcessor().getArch());
		
		System.out.println("********* ArrayUtils ***********");		
		int[] a = new int[] {};
		System.out.println(ArrayUtils.isEmpty(a));
		
		int[] b = new int[5];
		System.out.println(ArrayUtils.isEmpty(b));
		
		Integer[] c = new Integer[] {1, 2, 3, 4};
		Integer[] t = ArrayUtils.insert(2, c, 6); // Apache Commons
		printArray(t);
		
		// Even No.
		Arrays.stream(t).filter(xy -> xy%2==0).forEach(System.out::println);
		
		System.out.println("******** CharUtils ***********");
		System.out.println(CharUtils.isAscii('a'));
		
		System.out.println("********* ClassUtils/MethodUtils/FieldUtils*********");		
		System.out.println(ClassUtils.getPackageName(CommonUtils.class));
		System.out.println(ClassUtils.getAllSuperclasses(CommonUtils.class));
		System.out.println(ClassUtils.getCanonicalName(CommonUtils.class));
		
		System.out.println("********* LocalUtils *********");		
		System.out.println(LocaleUtils.availableLocaleList());
		System.out.println(LocaleUtils.countriesByLanguage("en_NU"));
		System.out.println(LocaleUtils.languagesByCountry("CA"));
		
		System.out.println("********* RangeUtils *********");
		System.out.println(Range.between(1, 3).getMaximum());
		
		System.out.println("********* StringUtils *********");
		System.out.println(StringUtils.getDigits("CHR 12,675.87"));
		
		System.out.println("********* SystemUtils *********");
		System.out.println(SystemUtils.IS_OS_WINDOWS_10);
		System.out.println(SystemUtils.getUserDir());
		System.out.println(SystemUtils.getUserHome());
		System.out.println(SystemUtils.getHostName());
		System.out.println(SystemUtils.getJavaIoTmpDir());
		System.out.println(SystemUtils.JAVA_VERSION);
		System.out.println(SystemUtils.JAVA_RUNTIME_VERSION);
		
		
		System.out.println("********* ThreadUtils *********");
		System.out.println(ThreadUtils.getAllThreadGroups());
		System.out.println(ThreadUtils.getAllThreads());
		
		System.out.println("********* Fraction *********");
		System.out.println(Fraction.FOUR_FIFTHS);
		
		Fraction f = Fraction.getFraction("2 1/2");
		System.out.println(f);
		System.out.println(f.invert());
		System.out.println(f.toProperString());
		
		Fraction f1 = Fraction.getFraction(2.5);
		System.out.println(f1.getDenominator());
		System.out.println(f1.getNumerator());
		System.out.println(f.getProperWhole());
		
		System.out.println("********* Mutable *********");
		MutableInt in = new MutableInt(1);
		System.out.println(in.addAndGet(5));
		
		System.out.println("********* WordUtils *********");
		System.out.println(WordUtils.swapCase("KriShNA  JwJwKnDeIeJiRtDj"));
	}

}
