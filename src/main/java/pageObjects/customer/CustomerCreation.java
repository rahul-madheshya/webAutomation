package pageObjects.customer;

import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.google.common.base.Verify;

import commonUtilities.AbstractUtility;

public class CustomerCreation extends AbstractUtility {

	WebDriver driver;

	public CustomerCreation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Create Customer']")
	WebElement buttonCreateCustomer;

	@FindBy(xpath = "//input[@name='aadharCardOnline']")
	WebElement inputAadharNumberOnline;

	@FindBy(xpath = "//button[text()='SEND OTP']")
	WebElement buttonSendOTP;

	@FindBy(xpath = "//span[text()='Information']//following::button[text()='x']")
	WebElement buttonClosePopupOnlineAadharVerificationFailed;

	@FindBy(xpath = "//input[@name='aadhaar_verification_mode'][@value='Biometric']")
	WebElement radioButtonAadharBiometric;

	@FindBy(xpath = "//span[text()='Biometric Verification']//following::button[text()='x']")
	WebElement buttonClosePopupAadharBiometricVerification;

	@FindBy(xpath = "//p[text()='Offline option enable due to biometric verification failure.']/ancestor::div[@role='dialog']/h2/button")
	WebElement buttonClosePopupAadharBiometricVerificationFailed;

	@FindBy(xpath = "//span/input[@value='Offline'][@name='aadhaar_verification_mode']")
	WebElement radioButtonAadharOffline;
	
	@FindBy(xpath = "//input[@name='aadharCardOffline']")
	WebElement inputAadharNumberOffline;
	
	@FindBy(xpath = "//button[text()='Verify']")
	WebElement buttonVerifyAadharNumberOffline;
	
	@FindBy(xpath = "//button[text()='Aadhaar(Multiple Upload)* ']")
	WebElement buttonUploadAadharImage;
	
	@FindBy(xpath = "//button[text()='Take Snapshot'][@id=':r1d:']")
	WebElement buttonCaptureAadharFrontImage;
	
	@FindBy(xpath = "//button[text()='Take Snapshot'][@id=':r1e:']")
	WebElement buttonCaptureAadharBackImage;
	
	
	public void createCustomer(String aadharNumber) throws InterruptedException, AWTException {
		driver.navigate().to("https://cggl-dev.capriglobal.in/customer-creation");
		inputKYCDetails(aadharNumber);
	}

	public void inputKYCDetails(String aadharNumber) throws InterruptedException, AWTException {
		inputAadharNumberOnline.sendKeys(aadharNumber);
		buttonSendOTP.click();
		fluentWait(buttonClosePopupOnlineAadharVerificationFailed);
		buttonClosePopupOnlineAadharVerificationFailed.click();
		waitforSecond(2);
		performKeyboardAction("TAB", 6);
		performKeyboardAction("ARROW_RIGHT", 1);
		waitForElementToBeVisible(buttonClosePopupAadharBiometricVerification);
		buttonClosePopupAadharBiometricVerification.click();
		fluentWait(buttonClosePopupAadharBiometricVerificationFailed);
		buttonClosePopupAadharBiometricVerificationFailed.click();
		performKeyboardAction("ARROW_RIGHT", 1);
		inputAadharNumberOffline.sendKeys(aadharNumber);
		buttonVerifyAadharNumberOffline.click();
		buttonUploadAadharImage.click();
		
		buttonCaptureAadharFrontImage.click();
		waitForElementToBeVisible(buttonCaptureAadharFrontImage);
		buttonCaptureAadharFrontImage.click();
		waitForElementToBeVisible(buttonCaptureAadharFrontImage);
		buttonCaptureAadharFrontImage.click();
		buttonCaptureAadharBackImage.click();
		waitForElementToBeVisible(buttonCaptureAadharBackImage);
		buttonCaptureAadharBackImage.click();
		waitForElementToBeVisible(buttonCaptureAadharBackImage);
		buttonCaptureAadharBackImage.click();
		
	}
}
