package commonUtilities;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.baseSetup.BaseSetup;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.NoSuchElementException;
import java.util.stream.IntStream;

public class AbstractUtility {

	WebDriver driver;

	public AbstractUtility(WebDriver driver) {
		this.driver = driver;
	}

	protected void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected void fluentWait(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(35))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForAlertAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}
	
	protected String getAlertMessage(WebElement element) {
		return driver.switchTo().alert().getText();
	}
	
	protected void performKeyboardAction(String action, int count) {
		Actions actions = new Actions(driver);
		switch (action) {
		case "TAB":
			IntStream.range(0, count).forEach(i -> actions.sendKeys(Keys.TAB).perform());
		case "ARROW_RIGHT":
			IntStream.range(0, count).forEach(i -> actions.sendKeys(Keys.ARROW_RIGHT).perform());
		}
	}

	protected void waitforSecond(double sec) throws InterruptedException {
		Thread.sleep((long) (sec * 1000));
	}

	public static int generateRandomNumber() {
		Random random = new Random();
		int random7DigitNumber = 1000000 + random.nextInt(9000000);
		return random7DigitNumber;
	}
}
