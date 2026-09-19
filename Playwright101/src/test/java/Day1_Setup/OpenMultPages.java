package Day1_Setup;

import com.microsoft.playwright.*;

public class OpenMultPages {
    public static void main(String[] args) {

        //try with resources
        try (Playwright obj_playwright = Playwright.create()) { //Playwrite.create() Starts Playwrite driver process

            //Launch browser
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //Select Chromium Engine                                 //Starts Chromium Browser     //Settings browser

            //Create browser context obj
            BrowserContext obj_context = obj_browser.newContext(); //isolated browser context

            //Create Page obj
            Page obj_page = obj_context.newPage();  // New Tab inside a page


            obj_page.navigate("https://example.com");
            System.out.println("Page title:" + obj_page.title());
            Thread.sleep(2000);

//-------------------------------------------------------------------------------------------------
            //Create browser context obj
            BrowserContext obj_context1 = obj_browser.newContext(); //isolated browser context

            //Create Page obj
            Page obj_page1 = obj_context.newPage();  // New Tab inside a page


            obj_page1.navigate("https://google.com");
            System.out.println("Page title:" + obj_page.title());
            Thread.sleep(2000);
            //-------------------------------------------------------

            obj_page.close();
        } catch (Exception e) {
            System.out.println("test field with an exception");
            e.printStackTrace();
        }
    }}
