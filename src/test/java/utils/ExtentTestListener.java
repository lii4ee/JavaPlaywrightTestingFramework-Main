package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;

import org.example.AppTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Base64;

public class ExtentTestListener implements ITestListener {

    // Setup the report engine directly here instead of using a separate manager class
    private static final ExtentReports extent = createExtentInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports createExtentInstance() {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");

        spark.config().setReportName("Playwright Automation Test Results");
        spark.config().setDocumentTitle("Test Execution Report");
        spark.config().setTheme(Theme.STANDARD);

        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        return extentReports;
    }

    private Page getPageInstance(ITestResult result) {
        // Update this line to return your framework's active Playwright Page object
        Object instance = result.getInstance();
        return instance instanceof AppTest
            ? ((AppTest) instance).getPage()
            : null;
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        Page page = getPageInstance(result);
        if (page != null) {
            byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            String base64Screenshot = Base64.getEncoder().encodeToString(buffer);
            test.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
