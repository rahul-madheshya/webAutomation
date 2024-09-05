package pageObjects.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerSearch {

	WebDriver driver;

	public CustomerSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/button[@value='customer_id']")
	WebElement searchByCustomerId;
	
	@FindBy(name = "customer_id")
	WebElement customerId;
	
	@FindBy(xpath = "//div/button[@value='full_name_dob']")
	WebElement searchByFullName;
	
	@FindBy(name = "full_name")
	WebElement fullName;
	
	@FindBy(xpath = "//button[text()='Create Customer']")
	WebElement buttonCustomerDedupe;
	
	public void goToCustomerDedupe() 
	{
		buttonCustomerDedupe.click();
	}
}
