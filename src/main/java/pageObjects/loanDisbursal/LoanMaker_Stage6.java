package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage6 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage6(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage6;

	public void submit_FundTrasferDetails() throws InterruptedException {
		waitForElementToBeClickable(button_submitMakerStage6);
		button_submitMakerStage6.click();
	}
}
