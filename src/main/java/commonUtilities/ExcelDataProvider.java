package commonUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider{
	
	public static String EXCEL_DATA_SHEET_NAME;
		
	@DataProvider(name = "excelData")
	public Object[][] excelDataProvider() throws IOException {
		
		Object[][] arrObj = getExcelData((System.getProperty("user.dir") + "//src//test//resources//TestData.xlsx"),
				EXCEL_DATA_SHEET_NAME);
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
				}}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}

	@SuppressWarnings("unused")
	private XSSFRow getRow(int i) {
		return null;
	}
}