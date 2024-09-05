package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;

import commonUtilities.ExcelDataProvider;
import configuration.baseSetup.BaseSetup;
import pageObjects.login.LoginPage;
import pageObjects.schemeMaster.SchemeMaster;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CreateScheme extends BaseSetup {

	private LoginPage loginPage;
	private SchemeMaster schemeMaster;

	@BeforeClass
	void setupTest() {
		ExcelDataProvider.EXCEL_DATA_SHEET_NAME = "SchemeMaster";
		
		// Initialize LoginPage and SchemeMaster objects
		loginPage = new LoginPage(driver);
		schemeMaster = new SchemeMaster(driver);
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	void createScheme(String schemeName, String schemeCalendar, String schemeDescription, String schemeType,
			String schemeLTV, String schemeMinimumLoanAmount, String schemeMaximumLoanAmount,
			String schemeRateOfInterest, String schemeLoanTenure, String schemeAdditionalRateOfInterest,
			String schemeRepaymentFrequency, String schemeFeeName, String schemeFeeType, String schemeFeeAmount)
			throws InterruptedException {

		try {

			// Start logging the test case in the Extent Report
			test = extent.createTest("Create Scheme Test", "Test for creating a new scheme with provided data");

			// Log into the application
			loginPage.loginGoldLoan("CGCL2014");
			test.log(Status.PASS, "Logged in successfully with user CGCL2014");

			// Navigate to Scheme Master
			schemeMaster.navigateToSchemeMaster();
			test.log(Status.PASS, "Navigated to Scheme Master page");

			// Create a new scheme
			schemeMaster.createNewScheme(schemeName, schemeCalendar, schemeDescription, schemeType, schemeLTV,
					schemeMinimumLoanAmount, schemeMaximumLoanAmount, schemeRateOfInterest, schemeLoanTenure,
					schemeAdditionalRateOfInterest, schemeRepaymentFrequency, schemeFeeName, schemeFeeType,
					schemeFeeAmount);
			if (!schemeMaster.getValidationMessage().equalsIgnoreCase("Scheme name exists.")) {
				test.log(Status.PASS, "Scheme created successfully with name: " + schemeName);
			} else {
				test.log(Status.FAIL,
						"Scheme creation failed. Error message : " + "'" + schemeMaster.getValidationMessage() + "'");
			}

		} catch (Exception e) {
			// Log the failure in the report
			test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			throw e;
		}
	}
}
