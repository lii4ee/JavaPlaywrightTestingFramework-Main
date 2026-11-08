package methodCaller;

import static testscripts.LoginHomePage.navitageToPage;

import com.microsoft.playwright.Page;

import testscripts.LoginHomePage;

public class MethodCaller {
    public static void methodCallerMain(Page page) {
        System.out.println("Gets business flow and calls each method");
        
        String pageUrl = "https://mvnrepository.com/";
        LoginHomePage.navitageToPage(page, pageUrl);
    }
}
