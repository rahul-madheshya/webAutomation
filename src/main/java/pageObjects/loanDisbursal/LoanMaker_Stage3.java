package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage3 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage3(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[text()='Source Of Gold*']/parent::div/div/div")
	WebElement get_GoldSource;

	@FindBy(xpath = "//div[@id='menu-source_of_gold']/descendant::ul/li")
	List<WebElement> select_GoldSource;

	@FindBy(xpath = "//button[text()='Customer Photo* ']")
	WebElement button_CaptureCustomerLivePhoto;

	@FindBy(xpath = "//button[text()='Take Snapshot']")
	WebElement capture_CustomerLivePhoto;

	@FindBy(xpath = "//button[text()='Confirm']")
	WebElement button_ConfirmCapturedCustomerLivePhoto;

	@FindBy(xpath = "//label[text()='End Use Of Gold*']/parent::div/div/div")
	WebElement get_GoldEndUse;

	@FindBy(xpath = "//div[@id='menu-end_use_of_gold']/descendant::ul/li")
	List<WebElement> select_GoldEndUse;

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage3;

	public void input_GoldInformation() throws InterruptedException {
		waitforSecond(2);
		get_GoldSource.click();
		waitForElementsToBeVisible(select_GoldSource);
		select_GoldSource.get(0).click();
		get_GoldEndUse.click();
		select_GoldEndUse.get(0).click();
		waitForElementToBeClickable(button_CaptureCustomerLivePhoto);
		button_CaptureCustomerLivePhoto.click();
		waitforSecond(3);
		waitForElementToBeClickable(capture_CustomerLivePhoto);
		capture_CustomerLivePhoto.click();
		waitForAlertAndAccept();
		waitForElementToBeClickable(button_ConfirmCapturedCustomerLivePhoto);
		button_ConfirmCapturedCustomerLivePhoto.click();
		waitForElementToBeClickable(button_submitMakerStage3);
		button_submitMakerStage3.click();
		
	}

}
