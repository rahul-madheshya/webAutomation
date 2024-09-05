package pageObjects.loanDisbursal;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage1 extends AbstractUtility{

	WebDriver driver;

	public LoanMaker_Stage1(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[text()='Item Name*']/parent::div/div/div")
	WebElement get_ItemName;

	@FindBy(xpath = "//div[@id='menu-item_name']/descendant::ul/li")
	List<WebElement> select_Item;

	@FindBy(xpath = "//input[@name='item_count']")
	WebElement input_ItemCount;
	
	@FindBy(xpath = "//input[@name='total_weight']")
	WebElement input_TotalWeight;

	@FindBy(xpath = "//input[@name='breads_stone_weight']")
	WebElement input_BeadsStoneWeight;
	
	@FindBy(xpath = "//label[text()='Purity (in Carat)*']/parent::div/div/div")
	WebElement get_Purity;
	
	@FindBy(xpath = "//div[@id='menu-purity']/descendant::ul/li")
	List<WebElement> select_Purity;
	
	@FindBy(xpath = "//button[text()='Ornament Live Photo* ']")
	WebElement button_CaptureOrnamentLivePhoto;
	
	@FindBy(xpath = "//button[text()='Take Snapshot']")
	WebElement capture_OrnamentLivePhoto;
	
	@FindBy(xpath = "//button[text()='Confirm']")
	WebElement button_ConfirmCapturedOrnamentLivePhoto;
	
	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage1;
	
	public void input_CollateralDetails() throws InterruptedException {
		get_ItemName.click();
		select_Item.get(1).click();;
		input_ItemCount.sendKeys("1");
		input_TotalWeight.sendKeys("10");
		input_BeadsStoneWeight.sendKeys("0.1");
		get_Purity.click();
		select_Purity.get(1).click();
		waitforSecond(1);
		button_CaptureOrnamentLivePhoto.click();
		waitforSecond(2);
		capture_OrnamentLivePhoto.click();
		waitforSecond(2);
		button_ConfirmCapturedOrnamentLivePhoto.click();
		waitForElementToBeClickable(button_submitMakerStage1);
		button_submitMakerStage1.click();
		
		
	}
}
