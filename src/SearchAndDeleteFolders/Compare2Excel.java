package SearchAndDeleteFolders;

import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Compare2Excel {

    private static final String firstExcelPath = SystemUtils.getUserDir() + "\\TestDataNew.xlsx";
    private static final String secondExcelPath = SystemUtils.getUserDir() + "\\TestDataOld.xlsx";

    public static void main(String[] args) throws Exception {

//      String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

 //       printMismatchForAllSheets();
        printMismatchForGivenSheetName("GB");
    }

    /**
     * Prints mismatch for given sheet name in 2 excels.
     *
     * @param sheetName
     * @throws Exception
     */
    private static void printMismatchForGivenSheetName(String sheetName) throws Exception {

        Workbook one = getWorkBook(firstExcelPath);
        Workbook two = getWorkBook(secondExcelPath);

        Map<String, String> dataFromASheetFor1stExcel = getAllDataFromExcelInFormOfListOfMaps(one, sheetName);
        Map<String, String> dataFromASheetFor2ndExcel = getAllDataFromExcelInFormOfListOfMaps(two, sheetName);

        System.out.println("****************************** SHEET '" + sheetName + "' COMPARISION STARTS **************************");
        printMismatch(dataFromASheetFor1stExcel, dataFromASheetFor2ndExcel);
        System.out.println("****************************** SHEET '" + sheetName + "' COMPARISION FINISHED ***************************");
    }

    /**
     * Returns {@link Workbook} Instance For An Excel File. For Both xls and xlsx.
     * @param path {@link String} Complete Excel File Path
     * @return {@link Workbook} Instance For An Excel File. For Both xls and xlsx.
     * @throws Exception
     */
    private static Workbook getWorkBook(String path) throws Exception {

//        System.out.println("-------- Loading File " + path + " --------");

        FileInputStream inStream = FileUtils.openInputStream(FileUtils.getFile(path));

//        System.out.println("---- Loading Finished ----- ");
        return WorkbookFactory.create(inStream);
    }

    /**
     * prints 1st Column And 2nd Column Mismatch
     * @throws Exception
     */
    private static void printMismatchForAllSheets() throws Exception {

        Workbook one = getWorkBook(firstExcelPath);
        Workbook two = getWorkBook(secondExcelPath);

        String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

        Arrays.asList(arr).forEach(a -> {

            Map<String, String> dataFromASheetFor1stExcel = getAllDataFromExcelInFormOfListOfMaps(one, a);
            Map<String, String> dataFromASheetFor2ndExcel = getAllDataFromExcelInFormOfListOfMaps(two, a);

            System.out.println("\n******************** SHEET '" + a + "' COMPARISION STARTS *****************");
            printMismatch(dataFromASheetFor1stExcel, dataFromASheetFor2ndExcel);
            System.out.println("******************** SHEET '" + a + "' COMPARISION FINISHED *****************\n");
        });
    }

    /**
     * Returns A Map With 1st Column As Key And 2nd Column as Value.
     *
     * @param work {@link Workbook} Instance For An Excel File. For Both xls and xlsx.
     * @param sheetName {@link String} Sheet Name
     * @return {@link Map<String, String>} With 1st Columns Of The Sheet As Keys And Corresponding 2nd Column As It's Value.
     */
    private static Map<String, String> getAllDataFromExcelInFormOfListOfMaps(Workbook work, String sheetName) {

//        System.out.println("----- Collecting Data From Sheet " + sheetName + " ------");

        Sheet s = work.getSheet(sheetName);
        Objects.requireNonNull(s, "Sorry! This Sheet Not Found The Excel File");
        Iterator<Row> itr = s.rowIterator();
        Map<String, String> m = new HashMap<>();
        Row.MissingCellPolicy policy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
//        int i = 0;
        while (itr.hasNext()) {
//          System.out.println("Iterating Next Row - " + i);
            Row row = itr.next();
            Cell first = row.getCell(0);
            String key = first != null ? getCellValue(first) : LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss_SSS"));

            Cell second = row.getCell(1);
            String val = second != null ? getCellValue(second) : "";

            String obj = m.put(key, val);
            if (obj != null) System.err.println("Multiple Keys Found In The Same Row - " + key);
//            i++;
        }

//        System.out.println("----- Collection Finished ------");
        return m;
    }

    /**
     * Returns A Map With 1st Column As Key And 2nd Column as Value.
     *
     * @param work {@link Workbook} Instance For An Excel File. For Both xls and xlsx.
     * @param sheetIndex {@link Integer} Sheet Index
     * @return {@link Map<String, String>} With 1st Columns Of The Sheet As Keys And Corresponding 2nd Column As It's Value.
     */
    private static Map<String, String> getAllDataFromExcelInFormOfListOfMaps(Workbook work, int sheetIndex) {

 //       System.out.println("----- Collecting Data From Sheet Index " + sheetIndex + " ------");

        Sheet s = work.getSheetAt(sheetIndex);
        Objects.requireNonNull(s, "Sorry! This Sheet Not Found The Excel File");
        Iterator<Row> itr = s.rowIterator();
        Map<String, String> m = new HashMap<>();
        int i = 0;
        while (itr.hasNext()) {
//          System.out.println("Iterating Next Row - " + i);
            Row row = itr.next();
            Cell first = row.getCell(0);
            String key = first != null ? getCellValue(first) : LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss_SSS"));

            Cell second = row.getCell(1);
            String val = second != null ? getCellValue(second) : "";

            String obj = m.put(key, val);
            if (obj != null) System.err.println("Multiple Keys Found In The Same Row - " + key);
//            i++;
        }

        System.out.println("----- Collection Finished ------");
        return m;
    }

    /**
     * Method To Print The Mismatch Key And Mismatch Value Between 2 Maps
     *
     * @param x {@link Map<String, String>}
     * @param y {@link Map<String, String>}
     */
    private static void printMismatch(Map<String, String> x, Map<String, String> y) {

        Objects.requireNonNull(x, "First Map Is Found Null");
        Objects.requireNonNull(y, "Second Map Is Found Null");

        printKeyDiff(x, y);

        for (String str : x.keySet()) {

            if (!y.containsKey(str)) {

                System.err.println("This Key Present In 1st Excel Not Found In 2nd Row Of The 1st Excel ->" + str);
            } else {

                String valueFrom1stMap = x.get(str);
                String valueFrom2ndMap = y.get(str);

//                System.out.println("----- Comparing ------ :: ");
//                System.out.println(valueFrom1stMap+"--- Versus --- "+valueFrom2ndMap);

                if (valueFrom1stMap != null && valueFrom1stMap.equals(valueFrom2ndMap)) {
//                  System.out.println("No Mismatch For : "+str);
                } else {
                    System.out.println("-------------------------------------------------------------------------");
                    System.err.println("Mismatch : Value For Key '" + str + "' In First File Is ->" + valueFrom1stMap);
                    System.err.println("Mismatch : Value For Key '" + str + "' In Second File Is ->" + valueFrom2ndMap);
                    System.out.println("-------------------------------------------------------------------------");
                }
            }
        }
    }

    /**
     * Retrieve {@link Cell} Value From Depending Upon Cell Type In String Form.
     * @param cell {@link Cell} Instance
     * @return {@link String} Cell Value In {@link String} Format
     */
    private static String getCellValue(Cell cell) {

        Objects.requireNonNull(cell, "Can't Fetch Value From null Cell");

        String output = "";

        switch (cell.getCellType()) {

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

    /**
     * prints Keys Of {@link Map<String, String>} Which Are Not Present In Another {@link Map<String, String>}
     * @param one {@link Map<String, String>}
     * @param two {@link Map<String, String>}
     */
    private static void printKeyDiff(Map<String, String> one, Map<String, String> two) {

        System.out.println("Printing All Keys Which Are Not Present In First File But Present In Second File");
        System.err.println(Sets.difference(one.keySet(), two.keySet()));

        System.out.println("Printing All Keys Which Are Not Present In Second File But Present In First File");
        System.err.println(Sets.difference(one.keySet(), two.keySet()));
    }

//    private static void printDiffOfSets(Set<String> a, Set<String> b) {
//
//        System.out.println(Sets.difference(a, b));
//        Set<String> tempOneOne = a;
//        Set<String> tempOneTwo = b;
//
//        tempOneOne.removeAll(tempOneTwo);
//
//        if (!tempOneOne.isEmpty()) {
//            System.err.println(tempOneOne);
//       } else {
//            System.out.println("There Is No Key Mismatch");
//        }
//    }

    /**
     * Searches Provided {@link String} Key In Each Sheet Of The Provided Excel file Path.
     * File Must Have Sheet Name As ["UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"] Only
     *
     * @param key {@link String} Search Key
     * @param filePath {@link String} Complete Path Of The Excel File
     * @throws Exception
     */
    private static void printIfThisKeyPresentInFile(String key, String filePath) throws Exception {

        Workbook one = getWorkBook(filePath);

        String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

        Arrays.asList(arr).forEach(a -> {

            Map<String, String> dataFromASheetForTheExcel = getAllDataFromExcelInFormOfListOfMaps(one, a);

            if (dataFromASheetForTheExcel.containsKey(key)) {

                System.err.println("THE SHEET '" + a + "' HAS THE '" + key + "' PRESENT IN IT");
                System.err.println("WHOSE VALUE IS ->" + dataFromASheetForTheExcel.get(key));
            }
        });
    }

    private static void printIfThisValuePresentInFile(String value, String filePath) throws Exception {

        Workbook one = getWorkBook(filePath);

        String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

        Arrays.asList(arr).forEach(a -> {

            Map<String, String> dataFromASheetForTheExcel = getAllDataFromExcelInFormOfListOfMaps(one, a);

            for (String str : dataFromASheetForTheExcel.keySet()) {

                String yes = dataFromASheetForTheExcel.get(str).equalsIgnoreCase(value) ? str : "";

                if (!yes.equals("")) {

                    System.err.println("Given Value '" + value + "' Found In Sheet '" + a + "' For Key ->" + str);
                    System.err.println("It's Complete Value Is ->" + dataFromASheetForTheExcel.get(str));
                }
            }
        });
    }

    private static void printIfThisKeyPartiallyPresentInFile(String key, String filePath) throws Exception {

        Workbook one = getWorkBook(filePath);

        String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

        Arrays.asList(arr).forEach(a -> {

            Map<String, String> dataFromASheetForTheExcel = getAllDataFromExcelInFormOfListOfMaps(one, a);

            for (String str : dataFromASheetForTheExcel.keySet()) {

                String found = str.toLowerCase().contains(key) ? str : "";

                if (!found.equals("")) {

                    System.err.println("Given Partial Key '" + key + "' Is Contained In '" + str + "' For Sheet '" + a + "");
                    System.err.println("It's Value Is ->" + dataFromASheetForTheExcel.get(str));
                }
            }
        });
    }

    private static void printIfThisValuePartiallyPresentInFile(String value, String filePath) throws Exception {

        Workbook one = getWorkBook(filePath);

        String[] arr = new String[]{"UK", "FR", "IT", "DE", "PT", "BE", "AT", "IE", "ES", "CH", "NL", "LU"};

        Arrays.asList(arr).forEach(a -> {

            Map<String, String> dataFromASheetForTheExcel = getAllDataFromExcelInFormOfListOfMaps(one, a);

            for (String str : dataFromASheetForTheExcel.keySet()) {

                String yes = dataFromASheetForTheExcel.get(str).toLowerCase().contains(value.toLowerCase()) ? str : "";

                if (!yes.equals("")) {
                    System.err.println("Given Value '" + value + "' Found In Sheet '" + a + "' For Key ->" + str);
                    System.err.println("It's Complete Value Is ->" + dataFromASheetForTheExcel.get(str));
                }
            }
        });
    }
}
