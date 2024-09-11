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
	private static String FILE_PATH = System.getProperty("user.dir") + "//src//main//resources//TestData.xlsx";

	@DataProvider(name = "excelData")
	public Object[][] excelDataProvider() throws IOException {

		Object[][] arrObj = getExcelData(FILE_PATH, EXCEL_DATA_SHEET_NAME);
		return arrObj;
	}

	@SuppressWarnings("resource")
	public String[][] getExcelData(String fileName, String sheetName) throws IOException {
		DataFormatter formatter = new DataFormatter();
		String[][] data = null;
		try {

			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sheet.getRow(i);
					cell = sheet.getRow(i).getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}

	// Method to write data to Excel file
	public static void writeDataToExcel(int rowIndex, String columnName, String data)
			throws IOException {
		try (FileInputStream fis = new FileInputStream(FILE_PATH); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(EXCEL_DATA_SHEET_NAME);
			XSSFRow headerRow = sheet.getRow(0);
			int colIndex = -1;

			// Find the column index based on column name
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
					colIndex = i;
					break;
				}
			}

			if (colIndex != -1) {
				XSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					row = sheet.createRow(rowIndex);
				}

				Cell cell = row.getCell(colIndex);
				if (cell == null) {
					cell = row.createCell(colIndex);
				}
				cell.setCellValue(data);
			}

			// Write the data back to the file
			try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
				workbook.write(fos);
			}
		}
	}

	@SuppressWarnings("unused")
	private XSSFRow getRow(int i) {
		return null;
	}

}
