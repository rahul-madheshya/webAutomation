package pageObjects.datePicker;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.AbstractUtility;

public class DatePicker extends AbstractUtility {

	public DatePicker(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@aria-label='Choose date']")
	WebElement datePicker;

	@FindBy(xpath = "//div[@class='css-epd502']/div/div/div/div/div")
	WebElement viewYear;

	@FindBy(xpath = "//div/descendant::div[@class='MuiYearPicker-root css-1qbc4x0-MuiYearPicker-root']/div/button")
	List<WebElement> selectYear;

	@FindBy(xpath = "//*[@data-testid='ArrowRightIcon']")
	WebElement selectMonth;

	@FindBy(xpath = "(//div[@role='row']/button)")
	List<WebElement> selectDay;

	public void selectDate(String year, String month, String day) throws InterruptedException {
		datePicker.click();
		selectYear(year);
		selectMonth(month);
		selectday(day);
	}

	void selectYear(String year) {
		waitForElementToBeClickable(viewYear);
		viewYear.click();
		for (int i = 0; i < selectYear.size(); i++) {
			if (selectYear.get(i).getText().equalsIgnoreCase(year)) {
				selectYear.get(i).click();
			}
		}
	}

	void selectMonth(String month) throws InterruptedException {
		waitForElementToBeClickable(selectMonth);
		for (int goToMonth = 1; goToMonth < Integer.parseInt(month); goToMonth++) {
			selectMonth.click();
		}
	}

	void selectday(String day) throws InterruptedException {
		selectDay.get(Integer.parseInt(day) - 1).click();
	}
}
