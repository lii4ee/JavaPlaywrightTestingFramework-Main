package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class AppTest {

    protected ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected ThreadLocal<Browser> browser = new ThreadLocal<>();
    protected ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    protected ThreadLocal<Page> page = new ThreadLocal<>();


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
