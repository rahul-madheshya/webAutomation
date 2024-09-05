package pageObjects.loanDisbursal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanDisbursalSearch {
	
	WebDriver driver;

	public LoanDisbursalSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='customer_id']")
	WebElement input_CustomerId;

	@FindBy(xpath = "//button[text()='Search']")
	WebElement button_searchCustomerId;
	
	@FindBy(xpath = "//button[text()='Create New Loan']")
	WebElement button_CreateNewLoan;
	
	public void getCustomerDetails(String customerId) {
		input_CustomerId.sendKeys(customerId);
		button_searchCustomerId.click();
		button_CreateNewLoan.click();;
	}
}
