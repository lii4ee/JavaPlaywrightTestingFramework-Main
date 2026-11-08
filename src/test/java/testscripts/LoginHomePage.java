package testscripts;

import com.microsoft.playwright.Page;

public class LoginHomePage {

    public static void navitageToPage(Page page, String pageURL){
        page.navigate(pageURL);
        try{
            Thread.sleep(10*1000);
        } catch (Exception e) {

        }

    }
}
