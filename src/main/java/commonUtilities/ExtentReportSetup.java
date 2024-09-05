package commonUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportSetup {

    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;

    // Method to set up the Extent Report
    public static void setUpExtentReport() {
        
    	// Specify the location of the report
        spark = new ExtentSparkReporter(System.getProperty("user.dir") +  "/ExtentReport.html");

        // Create an instance of ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Set some additional configuration
        spark.config().setReportName("Your Project Name");
        spark.config().setDocumentTitle("Automation Report");

        // Add environment information
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Rahul Madheshya");
    }

    // Method to close the report
    public static void tearDownExtentReport() {
        extent.flush();
    }
}
