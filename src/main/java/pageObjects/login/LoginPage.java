package pageObjects.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;
import configuration.baseSetup.BaseSetup;

public class LoginPage extends AbstractUtility {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	WebElement userEmail;

	@FindBy(name = "password")
	WebElement userPassword;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement buttonLogin;

	@FindBy(xpath = "//div/input[@role='combobox']")
	WebElement branch;

	@FindBy(xpath = "//li[@id='auto-complete-option-0']")
	WebElement selectEnteredBranch;

	@FindBy(xpath = "//div/button[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//*[@data-testid='AccountCircleIcon']/following::span/parent::button")
	WebElement buttonViewLoggedInUserDetails;

	@FindBy(xpath = "//*[@data-testid='LogoutIcon']")
	WebElement buttonLogout;

	@FindBy(xpath = "//div[@class='MuiAlert-message css-1pxa9xg-MuiAlert-message']")
	WebElement dailogErrorMessage;

	public void loginGoldLoan(String empcode) throws InterruptedException {
		String username = null, password = null;
		switch (empcode) {
		case "CGCL2014":
			username = BaseSetup.usernameMaker;
			password = BaseSetup.passwordMaker;
			break;
		case "CGCL002":
			username = BaseSetup.usernameChecker;
			password = BaseSetup.passwordChecker;
			break;
		default:
			throw new IllegalArgumentException("Invalid empcode: " + empcode);
		}
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		buttonLogin.click();
		waitForElementToBeVisible(branch);
		branch.click();
		branch.sendKeys(BaseSetup.homeBranch);
		selectEnteredBranch.click();
		submit.click();	}

	public void resetUsernamePassword() {
		userEmail.clear();
		userPassword.clear();
	}

	public String getLoginErrorMessage() {
		waitForElementToBeVisible(dailogErrorMessage);
		return dailogErrorMessage.getText();
	}

	public void logoutGoldLoan() {
		buttonViewLoggedInUserDetails.click();
		buttonLogout.click();
	}
}