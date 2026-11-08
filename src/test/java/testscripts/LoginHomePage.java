package testscripts;

import com.microsoft.playwright.Page;

public class LoginHomePage {

    public static void navitageToPage(Page page, String pageURL){
        page.navigate(pageURL);
    }
}
