package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanCreationChecker extends AbstractUtility {

	WebDriver driver;

	public LoanCreationChecker(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> checkboxCheckerCheckPoints;
	
	@FindBy(xpath = "//div/textarea[@id='outlined-basic']")
	WebElement inputCheckerRemarks;
	
	@FindBy(xpath = "//button[text()='Approve']")
	WebElement buttonApprove;
	
	@FindBy(xpath = "//div[text()='Are you sure you want to Approve the case?']/div/button[text()='Yes']")
	WebElement buttonConfirmCheckerSubmission;

	public void submit_CheckerRemarks() {
		for (int count =0; count <checkboxCheckerCheckPoints.size(); count++)
		{
			checkboxCheckerCheckPoints.get(count).click();
		}
		inputCheckerRemarks.sendKeys("Test");
		buttonApprove.click();
		waitForElementToBeClickable(buttonConfirmCheckerSubmission);
		buttonConfirmCheckerSubmission.click();
	}
}
