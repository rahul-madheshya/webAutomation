package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage7 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage7(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[text()='Disbursement Mode*']/parent::div/div/div")
	WebElement getListOfDisbursmentMode;

	@FindBy(xpath = "//div[@id='menu-net_disbursment_mode']/descendant::ul/li")
	List<WebElement> selectDisbursmentMode;

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage7;

	public void submit_NetDisbursementDetails() throws InterruptedException {
		waitForElementToBeClickable(getListOfDisbursmentMode);
		getListOfDisbursmentMode.click();
		selectDisbursmentMode.get(1).click();
		waitForElementToBeClickable(button_submitMakerStage7);
		button_submitMakerStage7.click();
	}
}
