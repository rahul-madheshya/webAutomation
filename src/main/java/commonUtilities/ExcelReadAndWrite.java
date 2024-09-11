package commonUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadAndWrite {

    public static String EXCEL_DATA_SHEET_NAME;
    private static final String FILE_PATH = System.getProperty("user.dir") + "//src//main//resources//TestData.xlsx";

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        return getExcelData(FILE_PATH, EXCEL_DATA_SHEET_NAME);
    }

    public String[][] getExcelData(String fileName, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = sheet.getRow(0).getLastCellNum();
            String[][] data = new String[noOfRows - 1][noOfCols];

            DataFormatter formatter = new DataFormatter();
            for (int i = 1; i < noOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < noOfCols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }
            return data;
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            throw e;
        }
    }

    // Method to write data to Excel file
    public static void writeDataToExcel(int rowIndex, String columnName, String data) throws IOException {
        try (FileInputStream fis = new FileInputStream(FILE_PATH); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(EXCEL_DATA_SHEET_NAME);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + EXCEL_DATA_SHEET_NAME);
            }

            int colIndex = getColumnIndex(sheet, columnName);
            if (colIndex == -1) {
                throw new IllegalArgumentException("Column not found: " + columnName);
            }

            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            Cell cell = row.getCell(colIndex, XSSFRow.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(data);

            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
            throw e;
        }
    }

    // Helper method to get the column index by name
    private static int getColumnIndex(XSSFSheet sheet, String columnName) {
        XSSFRow headerRow = sheet.getRow(0);
        if (headerRow == null) {
            return -1;
        }
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }
}
