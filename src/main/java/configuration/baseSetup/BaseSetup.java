package configuration.baseSetup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import commonUtilities.ExtentReportSetup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	protected WebDriver driver;
	public static String environment, baseUrl, usernameMaker, passwordMaker, usernameChecker, passwordChecker,
			homeBranch;
	private String browserType;
	private boolean isHeadless, isIncognito;
	private int timeout;
	protected ExtentReports extent;
    protected ExtentTest test;

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setupBrowser() {
		loadConfiguration();
		initializeExtentReport();
		switch (browserType.toLowerCase()) {
		case "chrome":
			setupChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserType);
		}

		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	private void setupChromeDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();

		if (isHeadless)
			chromeOptions.addArguments("--headless");
		if (isIncognito)
			chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--disable-webrtc-encryption", "--disable-webrtc-hw-decoding");
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 1);
		chromeOptions.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(chromeOptions);
	}

	@AfterSuite
	public void closeBrowser() {
		if (driver != null) {
			// driver.quit();
		}
		ExtentReportSetup.tearDownExtentReport();
	}

	private void loadConfiguration() {
		Properties config = new Properties();
		try (FileInputStream input = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/configuration.properties")) {
			config.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file", e);
		}

		environment = config.getProperty("environment");
		baseUrl = config.getProperty(environment + ".url");
		usernameMaker = config.getProperty(environment + ".CGCL2014.username");
		passwordMaker = config.getProperty(environment + ".CGCL2014.password");
		usernameChecker = config.getProperty(environment + ".CGCL002.username");
		passwordChecker = config.getProperty(environment + ".CGCL002.password");
		homeBranch = config.getProperty(environment + ".branch");
		browserType = config.getProperty("browser.type");
		isHeadless = Boolean.parseBoolean(config.getProperty("browser.headless"));
		isIncognito = Boolean.parseBoolean(config.getProperty("browser.incognito"));
		timeout = Integer.parseInt(config.getProperty("timeout"));
	}
	
	private void initializeExtentReport()
	{
		// Initialize the Extent Report before tests
		ExtentReportSetup.setUpExtentReport();
        extent = ExtentReportSetup.extent;
	
	}
}