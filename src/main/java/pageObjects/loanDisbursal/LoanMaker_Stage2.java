package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage2 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Consolidated Live Photo Of Ornaments* ']")
	WebElement button_CaptureOrnamentConsolidatedLivePhoto;

	@FindBy(xpath = "//button[text()='Take Snapshot']")
	WebElement capture_OrnamentConsolidatedLivePhoto;

	@FindBy(xpath = "//button[text()='Confirm']")
	WebElement button_ConfirmCapturedOrnamentConsolidatedLivePhoto;

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage2;

	public void input_ConsolidatedCollateralDetails() throws InterruptedException {
		button_CaptureOrnamentConsolidatedLivePhoto.click();
		waitforSecond(2);
		capture_OrnamentConsolidatedLivePhoto.click();
		waitforSecond(2);
		button_ConfirmCapturedOrnamentConsolidatedLivePhoto.click();
		waitforSecond(1);
		waitForElementToBeClickable(button_submitMakerStage2);
		button_submitMakerStage2.click();
	}
}
