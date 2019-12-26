package SearchAndDeleteFolders;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingExcel {

    /**
     * Retrieve {@link org.apache.poi.ss.usermodel.Cell} Value From Depending Upon Cell Type In String Form.
     *
     * @param cell
     *            {@link org.apache.poi.ss.usermodel.Cell} Instance
     * @return {@link String} Cell Value
     */
    public static String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {

        if (cell == null) {
            throw new RuntimeException("[Can Fetch Value From null Cell]");
        }

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

    private List<Map<String, String>> getListOfProductsDetails(String filePath, String sheetName) throws Exception {

        Sheet sheet = WorkbookFactory.create(Files.newInputStream(Paths.get(filePath), StandardOpenOption.READ)).getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        Row rowOne = sheet.getRow(0);
        int colCount = rowOne.getLastCellNum() - rowOne.getFirstCellNum();

        Row.MissingCellPolicy policy = Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> map;

        for (int i = 1; i < rowCount; i++) {

            map = new HashMap<>();
            Row row = sheet.getRow(i);

            for (int j = 0; j < colCount; j++) {
                map.put(rowOne.getCell(j).getStringCellValue(), row.getCell(j, policy).getStringCellValue());
            }
            data.add(map);
        }
        return data;
    }

}
