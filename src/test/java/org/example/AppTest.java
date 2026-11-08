package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.*;

import utils.ExtentReportManager;

public class AppTest {
    public static ExtentReports extent;
    protected ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected ThreadLocal<Browser> browser = new ThreadLocal<>();
    protected ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    protected ThreadLocal<Page> page = new ThreadLocal<>();

    public Page getPage() {
        return page.get();
    }

    @BeforeSuite
    public void setupSuite() {
        // This guarantees the engine launches BEFORE anything else runs
        extent = ExtentReportManager.initReport();
    }

    @AfterSuite
    public void tearDownSuite() {
        // This guarantees the HTML file writes out AFTER all tests finish
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod
    public void createPageContext() {
        // Initialize Playwright and launch a visible (headed) Chromium browser
        playwright.set(Playwright.create());
        browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));

        // Open a fresh isolated browser context and page before every @Test method
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    @AfterMethod
    public void closePageContext() {
        // Close the page and context to free up memory
        if (page.get() != null) {
            page.get().close();
            page.remove();
        }

        if (context.get() != null) {
            context.get().close();
            context.remove();
        }

        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }

        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }

}
