package commonUtilities;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import configuration.baseSetup.BaseSetup;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.stream.IntStream;

public class AbstractUtility {

	static WebDriver driver;

	public AbstractUtility(WebDriver driver) {
		AbstractUtility.driver = driver;
	}

	protected void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementsToBeVisible(List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	protected void selectDropDownValue(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	public static void captureScreenshot(String screenshotName) {
        // Create reference of TakesScreenshot and convert WebDriver instance to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot and store it in the source file
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Define the destination path where screenshot will be saved
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";

        // Create the destination file object
        File finalDestination = new File(destination);

        try {
            // Copy the screenshot to the destination
            FileUtils.copyFile(source, finalDestination);
            System.out.println("Screenshot taken: " + destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

	protected void fluentWait(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(35))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected boolean checkIfElementIsPresent(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected void waitForAlertAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	protected void scrollToElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
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

	public static int generateRandomNumber(int start, int end) {
		Random random = new Random();
		int randomNumber = start + random.nextInt(end);
		return randomNumber;
	}
}
