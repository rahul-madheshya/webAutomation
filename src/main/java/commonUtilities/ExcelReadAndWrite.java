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

	// Static variable to hold the sheet name for Excel operations.
	public static String EXCEL_DATA_SHEET_NAME;

	// File path to the Excel file located in the project's resources directory.
	private static final String FILE_PATH = System.getProperty("user.dir") + "//src//main//resources//TestData.xlsx";

	/**
	 * DataProvider to supply Excel data to TestNG tests. It reads data from the
	 * Excel sheet and returns it as a 2D array.
	 *
	 * @return 2D Object array containing Excel data.
	 * @throws IOException if the Excel file cannot be read.
	 */
	@DataProvider(name = "excelData")
	public Object[][] excelDataProvider() throws IOException {
		return getExcelData(FILE_PATH, EXCEL_DATA_SHEET_NAME);
	}

	/**
	 * Reads data from the specified Excel sheet and returns it as a 2D String
	 * array.
	 *
	 * @param fileName  The path to the Excel file.
	 * @param sheetName The name of the sheet from which to read data.
	 * @return 2D array of data from the Excel sheet.
	 * @throws IOException if the Excel file cannot be read.
	 */
	public String[][] getExcelData(String fileName, String sheetName) throws IOException {
		try (FileInputStream fis = new FileInputStream(fileName); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			// Get the specified sheet from the workbook.
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			// Get the number of rows and columns in the sheet.
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = sheet.getRow(0).getLastCellNum();
			String[][] data = new String[noOfRows - 1][noOfCols]; // Data array excluding header row.

			DataFormatter formatter = new DataFormatter(); // Formatter to convert cell values to String.
			for (int i = 1; i < noOfRows; i++) { // Loop through rows, starting from row 1 (skipping header).
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < noOfCols; j++) { // Loop through columns in each row.
					Cell cell = row.getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell); // Store formatted cell value in the array.
				}
			}
			return data; // Return the 2D array containing Excel data.
		} catch (IOException e) {
			System.err.println("Error reading Excel file: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Writes data to a specific cell in an Excel file based on row and column name.
	 *
	 * @param rowIndex   The row index where data needs to be written (0-based
	 *                   index).
	 * @param columnName The name of the column where data needs to be written.
	 * @param data       The data to write to the cell.
	 * @throws IOException if the Excel file cannot be written to.
	 */
	public static void writeDataToExcel(int rowIndex, String columnName, String data) throws IOException {
		try (FileInputStream fis = new FileInputStream(FILE_PATH); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			// Get the sheet from the workbook using the specified sheet name.
			XSSFSheet sheet = workbook.getSheet(EXCEL_DATA_SHEET_NAME);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + EXCEL_DATA_SHEET_NAME);
			}

			// Get the column index using the helper method based on the column name.
			int colIndex = getColumnIndex(sheet, columnName);
			if (colIndex == -1) {
				throw new IllegalArgumentException("Column not found: " + columnName);
			}

			// Get the row at the specified index, create it if it does not exist.
			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			// Get the cell at the specified column, create it if it does not exist, and set the new value.
			Cell cell = row.getCell(colIndex, XSSFRow.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellValue(data);

			// Write the updated workbook back to the Excel file.
			try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
				workbook.write(fos);
			}
		} catch (IOException e) {
			System.err.println("Error writing to Excel file: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Helper method to get the column index based on the column name.
	 *
	 * @param sheet      The Excel sheet object.
	 * @param columnName The name of the column to find.
	 * @return The index of the column, or -1 if the column is not found.
	 */
	private static int getColumnIndex(XSSFSheet sheet, String columnName) {
		XSSFRow headerRow = sheet.getRow(0); // Get the header row (row 0).
		if (headerRow == null) {
			return -1;
		}
		// Iterate through the header row to find the column with the matching name.
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
				return i; // Return the index of the matching column.
			}
		}
		return -1; // Return -1 if the column is not found.
	}
}
