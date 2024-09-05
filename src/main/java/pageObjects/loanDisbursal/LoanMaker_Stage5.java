package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage5 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage5(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage5;

	public void submit_FeeDetails() throws InterruptedException {
		waitForElementToBeClickable(button_submitMakerStage5);
		button_submitMakerStage5.click();
	}
}
