package pageObjects.loanDisbursal;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class LoanCreationDeviation extends AbstractUtility {

	WebDriver driver;

	public LoanCreationDeviation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[text()='Remarks']/parent::div/div/input")
	List<WebElement> inputDeviationRemarks;

	@FindBy(xpath = "//div[@data-field='decision']/div/div[@role='combobox']")
	List<WebElement> getListOfDeviationDecisions;

	@FindBy(xpath = "//div[@id='menu-']/descendant::ul/li")
	List<WebElement> selectDicision;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement buttonSubmit;

	@FindBy(xpath = "//div[text()='Are you sure you want to submit your decision?']/div/button[text()='Yes']")
	WebElement buttonConfirmDeviationSubmission;

	public void submit_DeviationRemarks() {
		IntStream.range(0, inputDeviationRemarks.size()).forEach(i -> {
			inputDeviationRemarks.get(i).sendKeys("Remarks " + (i + 1));
			getListOfDeviationDecisions.get(i).click();
			selectDicision.get(2).click();
		});
		waitForElementToBeVisible(buttonSubmit);
		scrollToElement(buttonSubmit);
		buttonSubmit.click();
		waitForElementToBeVisible(buttonConfirmDeviationSubmission);
		buttonConfirmDeviationSubmission.click();
	}

}
