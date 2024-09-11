package pageObjects.loanDisbursal;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanMaker_Stage4 extends AbstractUtility {

	WebDriver driver;

	public LoanMaker_Stage4(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[text()='Scheme Name*']/parent::div/div/div")
	WebElement getSchemeLists;

	@FindBy(xpath = "//div[@id='menu-scheme_name']/descendant::ul/li")
	List<WebElement> selectScheme;

	@FindBy(xpath = "//input[@name='requested_gold_loan_amount']")
	WebElement inputRequestedLoanAmount;

	@FindBy(xpath = "//button[@type='submit'][text()='Next']")
	WebElement button_submitMakerStage4;

	@FindBy(xpath = "//input[@name='minimum_loan_amount']")
	WebElement minimumLoanAmount;

	public void input_schemeDetails(String requestedLoanAmount, String schemeName) throws InterruptedException {
		getSchemeLists.click();
		IntStream.range(0, selectScheme.size()).forEach(i -> {
			if (selectScheme.get(i).getText().equalsIgnoreCase(schemeName)) {
				selectScheme.get(i).click();
			}
		});
		inputRequestedLoanAmount.sendKeys(requestedLoanAmount);
		waitForElementToBeClickable(button_submitMakerStage4);
		button_submitMakerStage4.click();
	}

	public String get_MinimumLoanAmount() {
		return minimumLoanAmount.getAttribute("value").toString();
	}
}
