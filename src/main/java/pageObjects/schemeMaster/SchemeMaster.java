package pageObjects.schemeMaster;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class SchemeMaster extends AbstractUtility {

	WebDriver driver;

	public SchemeMaster(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Scheme Master']")
	WebElement lbl_schemeMasterTile;

	@FindBy(xpath = "//button[text()='Add Scheme']")
	WebElement btn_AddSceheme;

	@FindBy(xpath = "//div/textarea[@name='scheme_name']")
	WebElement txt_SchemeName;

	@FindBy(xpath = "//label[text()='Scheme For*']/parent::div/div/div")
	WebElement lst_SchemeColendarsList;

	@FindBy(xpath = "//div[@id='menu-scheme_for']/descendant::ul/li")
	List<WebElement> ddl_SchemeColendar;

	@FindBy(xpath = "//div/input[@name='scheme_description']")
	WebElement txt_SchemeDescription;

	@FindBy(xpath = "//label[text()='Scheme Type*']/parent::div/div/div")
	WebElement lst_SchemeTypesList;

	@FindBy(xpath = "//div[@id='menu-scheme_type']/descendant::ul/li")
	List<WebElement> ddl_SchemeType;

	@FindBy(xpath = "//div/input[@name='scheme_rpg_ltv']")
	WebElement txt_SchemeLTV;

	@FindBy(xpath = "//div/input[@name='min_loan_amt']")
	WebElement txt_SchemeMinimumLoanAmount;

	@FindBy(xpath = "//div/input[@name='max_loan_amt']")
	WebElement txt_SchemeMaximumLoanAmount;

	@FindBy(xpath = "//div/input[@name='scheme_roi']")
	WebElement txt_SchemeRateOfInterest;

	@FindBy(xpath = "//div/input[@name='loan_tenure']")
	WebElement txt_SchemeLoanTenure;

	@FindBy(xpath = "//div/input[@name='additional_interest']")
	WebElement txt_SchemeAdditionalRateOfInterest;

	@FindBy(xpath = "//label[text()='Repayment Frequency *']/parent::div/div/div")
	WebElement lst_SchemeRepaymentFrequenciesList;

	@FindBy(xpath = "//div[@id='menu-repayment_frequency']/descendant::ul/li")
	List<WebElement> ddl_SchemeRepaymentFrequency;

	@FindBy(xpath = "//button[text()='Next']")
	WebElement btn_Next;

	@FindBy(xpath = "//label[text()='Fee Name']/parent::div/div/div")
	public WebElement lst_FeeNamesList;

	@FindBy(xpath = "//div[@id='menu-fee_name']/descendant::ul/li")
	List<WebElement> ddl_FeeName;

	@FindBy(xpath = "//label[text()='Fee Type*']/parent::div/div/div")
	public WebElement lst_FeeTypesList;

	@FindBy(xpath = "//div[@id='menu-fee_type']/descendant::ul/li")
	List<WebElement> ddl_FeeType;

	@FindBy(xpath = "//div/input[@name='fee_value']")
	WebElement txt_FeeAmount;

	@FindBy(xpath = "//button[text()='Save Scheme']")
	WebElement btn_SaveDetailsToCreateNewScheme;

	@FindBy(xpath = "(//div[@role='presentation']/div[@role='alert']/div[@class='MuiAlert-message css-1pxa9xg-MuiAlert-message'])[1]")
	WebElement dlg_ConfirmationMessage;

	@FindBy(xpath = "//button[text()='x']")
	WebElement btn_SchemeCreationWindow;

	public void navigateToSchemeMaster() {
		lbl_schemeMasterTile.click();
	}

	public void createNewScheme(String schemeName, String schemeColendar, String schemeDescription, String schemeType,
			String schemeLTV, String schemMiniumumLoanAmount, String schemMaximumLoanAmount, String schemRateOfInterest,
			String schemLoanTenure, String schemeAdditionalRateOfInterest, String schemeRepaymenetFrequency,
			String schemeFeeName, String schemeFeeType, String schemeFeeAmount) throws InterruptedException {
		waitForElementToBeClickable(btn_AddSceheme);
		btn_AddSceheme.click();
		txt_SchemeName.sendKeys(schemeName);
		lst_SchemeColendarsList.click();
		selectSchemeColendar(schemeColendar);
		txt_SchemeDescription.sendKeys(schemeDescription);
		lst_SchemeTypesList.click();
		selectSchemeType(schemeType);
		txt_SchemeLTV.sendKeys(schemeLTV);
		txt_SchemeMinimumLoanAmount.sendKeys(schemMiniumumLoanAmount);
		txt_SchemeMaximumLoanAmount.sendKeys(schemMaximumLoanAmount);
		txt_SchemeRateOfInterest.sendKeys(schemRateOfInterest);
		txt_SchemeLoanTenure.sendKeys(schemLoanTenure);
		txt_SchemeAdditionalRateOfInterest.sendKeys(schemeAdditionalRateOfInterest);
		lst_SchemeRepaymentFrequenciesList.click();
		selectSchemeRepaymentFrequency(schemeRepaymenetFrequency);
		btn_Next.click();
		waitforSecond(0.5);
		btn_Next.click();
		waitforSecond(0.25);
		lst_FeeNamesList.click();
		selectSchemeFeeName(schemeFeeName);
		waitforSecond(0.25);
		lst_FeeTypesList.click();
		selectSchemeFeeType(schemeFeeType);
		btn_Next.click();
		waitForElementToBeVisible(txt_FeeAmount);
		txt_FeeAmount.sendKeys(schemeFeeAmount);
		btn_Next.click();
		btn_SaveDetailsToCreateNewScheme.click();
	}

	private void selectSchemeColendar(String schemeColendar) {
		IntStream.range(0, ddl_SchemeColendar.size()).forEach(i -> {
			if (ddl_SchemeColendar.get(i).getText().equalsIgnoreCase(schemeColendar)) {
				ddl_SchemeColendar.get(i).click();
			}
		});
	}

	private void selectSchemeType(String schemeRepaymentFrequency) throws InterruptedException {
		waitforSecond(0.25);
		IntStream.range(0, ddl_SchemeType.size()).forEach(i -> {
			if (ddl_SchemeType.get(i).getText().equalsIgnoreCase(schemeRepaymentFrequency)) {
				ddl_SchemeType.get(i).click();
			}
		});
	}

	private void selectSchemeRepaymentFrequency(String schemeType) throws InterruptedException {
		waitforSecond(0.25);
		IntStream.range(0, ddl_SchemeRepaymentFrequency.size()).forEach(i -> {
			if (ddl_SchemeRepaymentFrequency.get(i).getText().equalsIgnoreCase(schemeType)) {
				ddl_SchemeRepaymentFrequency.get(i).click();
			}
		});
	}

	private void selectSchemeFeeName(String schemeFeeName) throws InterruptedException {
		waitforSecond(0.25);
		IntStream.range(0, ddl_FeeName.size()).forEach(i -> {
			if (ddl_FeeName.get(i).getText().equalsIgnoreCase(schemeFeeName)) {
				ddl_FeeName.get(i).click();
			}
		});
	}

	private void selectSchemeFeeType(String schemeFeeType) throws InterruptedException {
		waitforSecond(0.25);
		IntStream.range(0, ddl_FeeType.size()).forEach(i -> {
			if (ddl_FeeType.get(i).getText().equalsIgnoreCase(schemeFeeType)) {
				ddl_FeeType.get(i).click();
			}
		});
	}

	public void closeSchemeCreationWindow() {
		btn_SchemeCreationWindow.click();
	}

	@SuppressWarnings("unused")
	public String getValidationMessage() {
		waitForElementToBeVisible(dlg_ConfirmationMessage);
		return dlg_ConfirmationMessage.getText();
	}
}
