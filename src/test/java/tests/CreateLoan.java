package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import commonUtilities.AbstractUtility;
import commonUtilities.ExcelReadAndWrite;
import configuration.baseSetup.BaseSetup;
import pageObjects.loanDisbursal.CreateNewApplication;
import pageObjects.loanDisbursal.LoanCreationChecker;
import pageObjects.loanDisbursal.LoanCreationDeviation;
import pageObjects.loanDisbursal.LoanCreationDisbursement;
import pageObjects.loanDisbursal.LoanDisbursal;
import pageObjects.loanDisbursal.LoanDisbursalSearch;
import pageObjects.loanDisbursal.LoanMaker_Stage1;
import pageObjects.loanDisbursal.LoanMaker_Stage2;
import pageObjects.loanDisbursal.LoanMaker_Stage3;
import pageObjects.loanDisbursal.LoanMaker_Stage4;
import pageObjects.loanDisbursal.LoanMaker_Stage5;
import pageObjects.loanDisbursal.LoanMaker_Stage6;
import pageObjects.loanDisbursal.LoanMaker_Stage7;
import pageObjects.loanDisbursal.LoanMaker_Stage8;
import pageObjects.loanDisbursal.LoanMaker_Stage9;
import pageObjects.login.LoginPage;
import pageObjects.schemeMaster.SchemeMaster;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CreateLoan extends BaseSetup {

	private LoginPage loginPage;
	private LoanDisbursal loanDisbursal;
	private LoanDisbursalSearch loanDisbursalSearch;
	private CreateNewApplication createNewApplication;
	private LoanMaker_Stage1 loanMaker_Stage1;
	private LoanMaker_Stage2 loanMaker_Stage2;
	private LoanMaker_Stage3 loanMaker_Stage3;
	private LoanMaker_Stage4 loanMaker_Stage4;
	private LoanMaker_Stage5 loanMaker_Stage5;
	private LoanMaker_Stage6 loanMaker_Stage6;
	private LoanMaker_Stage7 loanMaker_Stage7;
	private LoanMaker_Stage8 loanMaker_Stage8;
	private LoanMaker_Stage9 loanMaker_Stage9;
	private LoanCreationDeviation loanCreationDeviation;
	private LoanCreationChecker loanCreationChecker;
	private LoanCreationDisbursement loanCreationDisbursement;
	private static Map<String, Integer> testMethodCounts = new HashMap<>(); // Keeps track of loop counts for each
																			// method

	@BeforeClass
	void setupTest() {
		ExcelReadAndWrite.EXCEL_DATA_SHEET_NAME = "LoanCreationData";

		// Initialize all the required page objects
		initializePageObjects();
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelReadAndWrite.class)
	void createScheme(String testCaseId, String testCaseName, String customerId, String loanAmount, String schemeName,
			String goldPouchNumber, String applicationNumber, Method method) throws Exception {
		int rowIndex = getTestMethodIndex(method) + 1;
		switch (testCaseId) {
		case "TC_01":
			try {

				// Start logging the test case in the Extent Report
				test = extent.createTest(testCaseName, "test for creating a new loan with provided data");

				// Log into the application
				loginPage.loginGoldLoan("CGCL2014");
				if (driver.getCurrentUrl().equalsIgnoreCase("https://cggl-dev.capriglobal.in/dashboard")) {
					test.log(Status.PASS, "logged in successfully with user CGCL2014");
				} else {
					test.log(Status.FAIL, "login failed");

				}

				// Navigate to Loan Disbursal
				loanDisbursal.navigateToLoanDisbursal();
				test.log(Status.INFO, "navigated to Loan Disbursal page");

				// Navigate to Create Loan Page
				loanDisbursal.navigateToCreateNewLoanPage();

				loanDisbursalSearch.getCustomerDetails(customerId);
				loanDisbursalSearch.proceedToNewLoanCreation();
				test.log(Status.INFO, "fetched customer details and proceeded to new loan creation");

				createNewApplication.input_AppliedLoanDetails(loanAmount);
				test.log(Status.INFO, "entered colendar and loan amount details to create new loan application");

				completeMakerJourney(loanAmount, schemeName, goldPouchNumber);
				test.log(Status.INFO, "entered rrquired details to complete all the stages of maker journey");

				logoutAndLoginWithEmployeeCode("CGCL002");
				loanDisbursal.navigateToLoanDisbursal();
				applicationNumber = loanDisbursal.getNewCreatedLoanApplicationNumber();
				ExcelReadAndWrite.writeDataToExcel(rowIndex, "Application_Number", applicationNumber);

				loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
				loanDisbursal.startWithSearchedApplication();
				loanCreationDeviation.submit_DeviationRemarks();
				loanDisbursal.startWithSearchedApplication();
				loanCreationChecker.submit_CheckerRemarks();
				test.log(Status.INFO,
						"logout Maker user and login with super user to complete the deviation and checker stage");

				logoutAndLoginWithEmployeeCode("CGCL2014");
				test.log(Status.INFO,
						"logout super user and login with maker user to complete disbursement of the newly created application");

				loanDisbursal.navigateToLoanDisbursal();
				loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
				loanDisbursal.startWithSearchedApplication();
				loanCreationDisbursement.submit_DisbursmentDetails();

				if (loanCreationDisbursement.getMessage().startsWith("Congratulations, the loan account number"))
					test.log(Status.PASS, loanCreationDisbursement.getMessage());
				else
					test.log(Status.FAIL, loanCreationDisbursement.getMessage());

			} catch (Exception exception) {
				// Log the failure in the report
				test.log(Status.FAIL, "Test failed due to: " + exception.getMessage());
				throw exception;
			}
			break;
		case "TC_02":
			try {

				// Start logging the test case in the Extent Report
				test = extent.createTest(testCaseName, "test for creating a new loan with provided data");

				if(!driver.getCurrentUrl().equalsIgnoreCase(baseUrl))
				{
					driver.navigate().to(baseUrl);
				}
				// Log into the application
				loginPage.loginGoldLoan("CGCL2014");
				if (driver.getCurrentUrl().equalsIgnoreCase("https://cggl-dev.capriglobal.in/dashboard")) {
					test.log(Status.PASS, "logged in successfully with user CGCL2014");
				} else {
					test.log(Status.FAIL, "login failed");

				}

				// Navigate to Loan Disbursal
				loanDisbursal.navigateToLoanDisbursal();
				test.log(Status.INFO, "navigated to Loan Disbursal page");

				// Navigate to Create Loan Page
				loanDisbursal.navigateToCreateNewLoanPage();

				loanDisbursalSearch.getCustomerDetails(customerId);
				loanDisbursalSearch.proceedToNewLoanCreation();
				test.log(Status.INFO, "fetched customer details and proceeded to new loan creation");

				createNewApplication.input_AppliedLoanDetails(loanAmount);
				test.log(Status.INFO, "entered colendar and loan amount details to create new loan application");

				completeMakerJourney(loanAmount, schemeName, goldPouchNumber);
				test.log(Status.INFO, "entered rrquired details to complete all the stages of maker journey");

				logoutAndLoginWithEmployeeCode("CGCL002");
				loanDisbursal.navigateToLoanDisbursal();
				applicationNumber = loanDisbursal.getNewCreatedLoanApplicationNumber();
				ExcelReadAndWrite.writeDataToExcel(rowIndex, "Application_Number", applicationNumber);

				loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
				loanDisbursal.startWithSearchedApplication();
				loanCreationDeviation.submit_DeviationRemarks();
				loanDisbursal.startWithSearchedApplication();
				loanCreationChecker.submit_CheckerRemarks();
				test.log(Status.INFO,
						"logout Maker user and login with super user to complete the deviation and checker stage");

				logoutAndLoginWithEmployeeCode("CGCL2014");
				test.log(Status.INFO,
						"logout super user and login with maker user to complete disbursement of the newly created application");

				loanDisbursal.navigateToLoanDisbursal();
				loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
				loanDisbursal.startWithSearchedApplication();
				loanCreationDisbursement.submit_DisbursmentDetails();

				if (loanCreationDisbursement.getMessage().startsWith("Congratulations, the loan account number"))
					test.log(Status.PASS, loanCreationDisbursement.getMessage());
				else
					test.log(Status.FAIL, loanCreationDisbursement.getMessage());

			} catch (Exception exception) {
				// Log the failure in the report
				test.log(Status.FAIL, "Test failed due to: " + exception.getMessage());
				throw exception;
			}
			break;
		}

	}

	private void initializePageObjects() {
		loginPage = new LoginPage(driver);
		loanDisbursal = new LoanDisbursal(driver);
		loanDisbursalSearch = new LoanDisbursalSearch(driver);
		createNewApplication = new CreateNewApplication(driver);
		loanMaker_Stage1 = new LoanMaker_Stage1(driver);
		loanMaker_Stage2 = new LoanMaker_Stage2(driver);
		loanMaker_Stage3 = new LoanMaker_Stage3(driver);
		loanMaker_Stage4 = new LoanMaker_Stage4(driver);
		loanMaker_Stage5 = new LoanMaker_Stage5(driver);
		loanMaker_Stage6 = new LoanMaker_Stage6(driver);
		loanMaker_Stage7 = new LoanMaker_Stage7(driver);
		loanMaker_Stage8 = new LoanMaker_Stage8(driver);
		loanMaker_Stage9 = new LoanMaker_Stage9(driver);
		loanCreationDeviation = new LoanCreationDeviation(driver);
		loanCreationChecker = new LoanCreationChecker(driver);
		loanCreationDisbursement = new LoanCreationDisbursement(driver);
	}

	private void completeMakerJourney(String loanAmount, String schemeName, String goldPouchNumber)
			throws InterruptedException {
		loanMaker_Stage1.input_CollateralDetails();
		loanMaker_Stage2.input_ConsolidatedCollateralDetails();
		loanMaker_Stage3.input_GoldInformation();
		loanMaker_Stage4.input_schemeDetails(loanAmount, schemeName);
		loanMaker_Stage5.submit_FeeDetails();
		loanMaker_Stage6.submit_FundTrasferDetails();
		loanMaker_Stage7.submit_NetDisbursementDetails();
		goldPouchNumber = "GLBS" + AbstractUtility.generateRandomNumber(1000000, 9999999);
		loanMaker_Stage8.input_AdditionalGoldInformation(goldPouchNumber, "1000");
		loanMaker_Stage9.submit_CustomerLoanDetails();

	}

	private void logoutAndLoginWithEmployeeCode(String empCode) throws InterruptedException {
		loginPage.logoutGoldLoan();
		loginPage.loginGoldLoan(empCode);
	}

	private int getTestMethodIndex(Method method) {
		// A static counter that increments with every invocation of the DataProvider
		String methodName = method.getName();
		// Track the loop count for this specific test case using method name as key
		if (!testMethodCounts.containsKey(methodName)) {
			testMethodCounts.put(methodName, 0);
		}
		int currentCount = testMethodCounts.get(methodName);
		testMethodCounts.put(methodName, currentCount + 1);
		return currentCount;
	}

}
