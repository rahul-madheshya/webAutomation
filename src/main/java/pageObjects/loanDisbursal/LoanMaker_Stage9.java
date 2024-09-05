package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage9 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage9(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[text()='Final Submit']")
	WebElement button_submitMakerStage9;

	public void submit_CustomerLoanDetails() {
		waitForElementToBeClickable(button_submitMakerStage9);
		button_submitMakerStage9.click();
	}
}
