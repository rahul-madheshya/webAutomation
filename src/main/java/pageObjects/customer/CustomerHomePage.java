package pageObjects.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerHomePage {

	WebDriver driver;

	public CustomerHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Customer 360']")
	WebElement customer360Tile;

	@FindBy(xpath = "//div[text()='Customer Creation']")
	WebElement customerCreationTile;

	@FindBy(xpath = "//div[text()='Customer Summary']")
	WebElement customerSummaryTile;
	
	@FindBy(xpath = "//div[text()='Customer Creation Queue']")
	WebElement customerCreationQueueTile;
	
	public void goToCustomerCreation()
	{
		customer360Tile.click();
		customerCreationTile.click();
	}
}
