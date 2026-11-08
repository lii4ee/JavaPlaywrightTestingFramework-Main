package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports initReport() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");

            spark.config().setReportName("Playwright Automation Test Results");
            spark.config().setDocumentTitle("Test Execution Report");
            spark.config().setTheme(Theme.STANDARD);

            extent.attachReporter(spark);
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
