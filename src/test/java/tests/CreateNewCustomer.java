package tests;


import java.awt.AWTException;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import commonUtilities.AbstractUtility;
import configuration.baseSetup.BaseSetup;
import pageObjects.customer.CustomerCreation;
import pageObjects.customer.CustomerDedupe;
import pageObjects.customer.CustomerHomePage;
import pageObjects.customer.CustomerSearch;
import pageObjects.datePicker.DatePicker;
import pageObjects.loanDisbursal.CreateNewApplication;
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

public class CreateNewCustomer extends BaseSetup {

	@Test
	void login() throws InterruptedException, AWTException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginGoldLoan("CGCL2014");

		CustomerHomePage customerHomePage = new CustomerHomePage(driver);
		customerHomePage.goToCustomerCreation();

		CustomerSearch customerSearch = new CustomerSearch(driver);
		customerSearch.goToCustomerDedupe();

		CustomerDedupe customerdedupe = new CustomerDedupe(driver);
		//customerdedupe.customerDedupe("Nikhil", "Kumar", "Ashok Kumar", "CTEPM1874A", "1455247324", "1990-11-17");
		
		CustomerCreation customerCreation = new CustomerCreation(driver);
		customerCreation.createCustomer("334455223323");
		
	}
}
