package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanCreationDisbursement extends AbstractUtility {

	WebDriver driver;

	public LoanCreationDisbursement(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Send OTP']")
	WebElement buttonSendOTP;

	@FindBy(xpath = "//input[@name='primaryContactNumberOTP']")
	WebElement inputOTP;

	@FindBy(xpath = "//button[text()='Verify']")
	WebElement buttonVerifyOTP;

	@FindBy(xpath = "//button[text()='Disburse']")
	WebElement buttonDisburse;

	@FindBy(xpath = "//div[@class='MuiAlert-message css-1pxa9xg-MuiAlert-message']/div")
	WebElement dailogErrorMessage;

	@FindBy(xpath = "(//div[@role='presentation']/div[@role='alert']/div[@class='MuiAlert-message css-1pxa9xg-MuiAlert-message'])[1]")
	WebElement dailogSucessfullMessage;

	public void submit_DisbursmentDetails() throws InterruptedException {
		buttonSendOTP.click();
		waitForElementToBeVisible(inputOTP);
		inputOTP.sendKeys("1111");
		waitForElementToBeVisible(buttonVerifyOTP);
		buttonVerifyOTP.click();
		waitforSecond(2);
		buttonDisburse.click();
	}

	public String getMessage() throws InterruptedException {
		String message = "No message available";
		waitforSecond(12);
		try {
			if (dailogSucessfullMessage.isDisplayed()) {
				message = dailogSucessfullMessage.getText();
			} else if (dailogErrorMessage.isDisplayed()) {
				message = dailogErrorMessage.getText();
			}
		} catch (Exception e) {
			// Log the exception if needed
			System.out.println("No alert message found: " + e.getMessage());
		}
		return message;
	}
}
