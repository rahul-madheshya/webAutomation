package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanDisbursal extends AbstractUtility {

	WebDriver driver;

	public LoanDisbursal(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Loan Disbursal']")
	WebElement loanDisbursalTile;

	@FindBy(xpath = "/button[text()='LAN']")
	WebElement selectSearchByLanNumber;

	@FindBy(xpath = "//input[@name='loan_account_no']")
	WebElement inputSearchByLanNumber;

	@FindBy(xpath = "//button[text()='Application No']")
	WebElement selectSearchByApplicationNumber;

	@FindBy(xpath = "//input[@name='application_no']")
	WebElement inputSearchByApplicationNumber;

	@FindBy(xpath = "//button[text()='Mobile No']")
	WebElement selectSearchByMobileNumber;

	@FindBy(xpath = "//input[@name='customer_mobile_number']")
	WebElement inputSearchByMobileNumber;

	@FindBy(xpath = "//button[@type='submit'][text()='Search']")
	WebElement search;

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	WebElement getSearchedRecords;

	@FindBy(xpath = "//div[@class='MuiDataGrid-virtualScrollerContent css-1kwdphh-MuiDataGrid-virtualScrollerContent']")
	WebElement checkIfNoRecordsFound;

	@FindBy(xpath = "//button[text()='Create New Loan']")
	WebElement button_CreateNewLoan;

	@FindBy(xpath = "//div[@data-id]/div[5][@data-field='application_no']")
	List<WebElement> get_ApplicationLists;

	public void navigateToLoanDisbursal() {
		loanDisbursalTile.click();
	}

	public void searchApplicationByLanNumber(String LanNumber) {
		selectSearchByLanNumber.click();
		inputSearchByLanNumber.sendKeys(LanNumber);
		search.click();
	}

	public void searchApplicationByApplicationNumber(String ApplicationNumber) {
		selectSearchByApplicationNumber.click();
		inputSearchByApplicationNumber.sendKeys(ApplicationNumber);
		search.click();
	}

	public void searchApplicationByMobileNumber(String MobileNumber) {
		selectSearchByMobileNumber.click();
		inputSearchByMobileNumber.sendKeys(MobileNumber);
		search.click();
	}

	public void navigateToCreateNewLoanPage() {
		button_CreateNewLoan.click();
	}
	
	public void startWithSearchedApplication()
	{
		get_ApplicationLists.get(0).click();
	}
	
	public String getNewCreatedLoanApplicationNumber()
	{
		return get_ApplicationLists.get(0).getText();
	}
}
