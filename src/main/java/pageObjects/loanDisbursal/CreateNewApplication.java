package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class CreateNewApplication extends AbstractUtility{

	WebDriver driver;

	public CreateNewApplication(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@role='combobox']")
	WebElement get_ColendarList;

	@FindBy(xpath = "//div[@id='menu-applied_for']/descendant::ul/li")
	List<WebElement> select_Colendar;

	@FindBy(xpath = "//input[@name='applied_laon_amount']")
	WebElement input_AppliedLoanAmount;
	
	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitNewLoanCreation;
	

	public void input_AppliedLoanDetails(String appliedLoanAmount) throws InterruptedException {
		get_ColendarList.click();
		select_Colendar.get(0).click();;
		input_AppliedLoanAmount.sendKeys(appliedLoanAmount);
		waitforSecond(1);
		waitForElementToBeClickable(button_submitNewLoanCreation);
		button_submitNewLoanCreation.click();
	}
}
