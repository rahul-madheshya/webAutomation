package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonUtilities.AbstractUtility;
import commonUtilities.ExcelDataProvider;
import configuration.baseSetup.BaseSetup;
import pageObjects.loanDisbursal.*;
import pageObjects.login.LoginPage;

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

	@BeforeClass
	void setupTest() {
		ExcelDataProvider.EXCEL_DATA_SHEET_NAME = "SchemeMaster";
		initializePageObjects();
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

	@Test
	void createLoan() throws InterruptedException {
		try {
			// Start logging the test case in the Extent Report
			test = extent.createTest("CreateLoan", "Test for creating a new scheme with provided data");

			// Step 1: Login as Maker
			loginAndNavigateToLoanPage("CGCL2014");
			test.log(Status.INFO, "Logged in successfully with user CGCL2014");

			// Step 2: Create new loan
			createLoanProcess("1344287", "15000");
			test.log(Status.INFO, "Created new loan application and completed maker journey");

			// Step 3: Logout as Maker and Login as Checker for approval
			approveLoan();
			test.log(Status.INFO, "Aprroved the deviation and completed checker journey");

			// Step 4: Finalize loan disbursement
			disburseLoan();
			test.log(Status.INFO, "Disbursed the loan");

		} catch (Exception e) {
			// Log the failure in the report
			test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			throw e;
		}
	}

	private void loginAndNavigateToLoanPage(String username) throws InterruptedException {
		loginPage.loginGoldLoan(username);
		loanDisbursal.navigateToLoanDisbursal();
		loanDisbursal.navigateToCreateNewLoanPage();
	}

	private void createLoanProcess(String customerId, String loanAmount) throws InterruptedException {
		loanDisbursalSearch.getCustomerDetails(customerId);
		createNewApplication.input_AppliedLoanDetails(loanAmount);
		completeLoanMakerStages(loanAmount);
	}

	private void completeLoanMakerStages(String loanAmount) throws InterruptedException {
		loanMaker_Stage1.input_CollateralDetails();
		loanMaker_Stage2.input_ConsolidatedCollateralDetails();
		loanMaker_Stage3.input_GoldInformation();
		loanMaker_Stage4.input_schemeDetails(loanAmount);
		loanMaker_Stage5.submit_FeeDetails();
		loanMaker_Stage6.submit_FundTrasferDetails();
		loanMaker_Stage7.submit_NetDisbursementDetails();
		loanMaker_Stage8.input_AdditionalGoldInformation(
				"GLBS" + AbstractUtility.generateRandomNumber(1000000, 9000000), "44.34");
		loanMaker_Stage9.submit_CustomerLoanDetails();
	}

	private void approveLoan() throws InterruptedException {
		logoutAndLoginAsChecker();
		String applicationNumber = loanDisbursal.getNewCreatedLoanApplicationNumber();
		loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
		loanCreationDeviation.submit_DeviationRemarks();
		loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
		loanCreationChecker.submit_CheckerRemarks();
	}

	private void logoutAndLoginAsChecker() throws InterruptedException {
		loginPage.logoutGoldLoan();
		loginPage.loginGoldLoan("CGCL002");
		loanDisbursal.navigateToLoanDisbursal();
	}

	private void disburseLoan() throws InterruptedException {
		loginPage.logoutGoldLoan();
		loginPage.loginGoldLoan("CGCL2014");
		loanDisbursal.navigateToLoanDisbursal();
		String applicationNumber = loanDisbursal.getNewCreatedLoanApplicationNumber();
		loanDisbursal.searchApplicationByApplicationNumber(applicationNumber);
		loanCreationDisbursement.submit_DisbursmentDetails();
	}
}
