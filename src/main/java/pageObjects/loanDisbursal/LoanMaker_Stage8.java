package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage8 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage8(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='gold_pouch_number']")
	WebElement inputGoldPouchNumber;
	
	@FindBy(xpath = "//input[@name='tare_weight_of_gold_packet']")
	WebElement inputGoldPouchTareWeight;
	
	@FindBy(xpath = "//label[text()='Justification*']/parent::div/div/input")
	List<WebElement> input_Justification;
	
	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage8;

	public void input_AdditionalGoldInformation(String goldPouchNumber, String goldPouchTareWeight) throws InterruptedException {
		inputGoldPouchNumber.sendKeys(goldPouchNumber);
		inputGoldPouchTareWeight.sendKeys(goldPouchTareWeight);
		for(int count=0; count<input_Justification.size(); count++)
		{
			input_Justification.get(count).sendKeys("Justification Remarks " + (count+1));
			Thread.sleep(500);
		}
		waitForElementToBeClickable(button_submitMakerStage8);
		button_submitMakerStage8.click();
	}
}
