package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import commonUtilities.AbstractUtility;
import configuration.baseSetup.BaseSetup;
import pageObjects.customer.CustomerDedupe;
import pageObjects.customer.CustomerHomePage;
import pageObjects.customer.CustomerSearch;
import pageObjects.datePicker.DatePicker;
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

public class CreateLoan extends BaseSetup {
	
	
	@Test
	void login() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginGoldLoan("CGCL2014");

		LoanDisbursal loanDisbursal = new LoanDisbursal(driver);
		loanDisbursal.navigateToLoanDisbursal(); 
		// loanDisbursal.searchApplicationByApplicationNumber("GL4082929818");
		loanDisbursal.navigateToCreateNewLoanPage();

		LoanDisbursalSearch loanDisbursalSearch = new LoanDisbursalSearch(driver);
		loanDisbursalSearch.getCustomerDetails("1344287");

		CreateNewApplication createNewApplication = new CreateNewApplication(driver);
		createNewApplication.input_AppliedLoanDetails("15000");

		LoanMaker_Stage1 loanMaker_Stage1 = new LoanMaker_Stage1(driver);
		loanMaker_Stage1.input_CollateralDetails();

		LoanMaker_Stage2 loanMaker_Stage2 = new LoanMaker_Stage2(driver);
		loanMaker_Stage2.input_ConsolidatedCollateralDetails();

		LoanMaker_Stage3 loanMaker_Stage3 = new LoanMaker_Stage3(driver);
		loanMaker_Stage3.input_GoldInformation();

		LoanMaker_Stage4 loanMaker_Stage4 = new LoanMaker_Stage4(driver);
		loanMaker_Stage4.input_schemeDetails("15000");

		LoanMaker_Stage5 loanMaker_Stage5 = new LoanMaker_Stage5(driver);
		loanMaker_Stage5.submit_FeeDetails();

		LoanMaker_Stage6 loanMaker_Stage6 = new LoanMaker_Stage6(driver);
		loanMaker_Stage6.submit_FundTrasferDetails();

		LoanMaker_Stage7 loanMaker_Stage7 = new LoanMaker_Stage7(driver);
		loanMaker_Stage7.submit_NetDisbursementDetails();

		LoanMaker_Stage8 loanMaker_Stage8 = new LoanMaker_Stage8(driver);
		loanMaker_Stage8.input_AdditionalGoldInformation(("GLBS" + AbstractUtility.generateRandomNumber(1000000, 9000000)), "44.34");

		LoanMaker_Stage9 loanMaker_Stage9 = new LoanMaker_Stage9(driver);
		loanMaker_Stage9.submit_CustomerLoanDetails();

		loginPage.logoutGoldLoan();
		loginPage.loginGoldLoan("CGCL002");

		loanDisbursal.navigateToLoanDisbursal();
		loanDisbursal.searchApplicationByApplicationNumber(loanDisbursal.getNewCreatedLoanApplicationNumber());

		LoanCreationDeviation loanCreationDeviation = new LoanCreationDeviation(driver);
		loanCreationDeviation.submit_DeviationRemarks();
		
		loanDisbursal.searchApplicationByApplicationNumber(loanDisbursal.getNewCreatedLoanApplicationNumber());
		
		LoanCreationChecker loanCreationChecker = new LoanCreationChecker(driver);
		loanCreationChecker.submit_CheckerRemarks();
	
		loginPage.logoutGoldLoan();
		loginPage.loginGoldLoan("CGCL2014");
		
		loanDisbursal.navigateToLoanDisbursal();
		loanDisbursal.searchApplicationByApplicationNumber(loanDisbursal.getNewCreatedLoanApplicationNumber());
		
		LoanCreationDisbursement loanCreationDisbursement = new LoanCreationDisbursement(driver);
		loanCreationDisbursement.submit_DisbursmentDetails();
	}	
}
