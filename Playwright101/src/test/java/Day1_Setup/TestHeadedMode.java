package Day1_Setup;

import com.microsoft.playwright.*;

public class TestHeadedMode {
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
            System.out.println("Browser Version" + obj_browser.version());
            System.out.println("Page title:" + obj_page.title());
            System.out.println("Playwright installation works correctly");

            obj_page.waitForTimeout(2000);

            obj_page.close();
        } catch (Exception e) {
            System.out.println("test field with an exception");
            e.printStackTrace();
        }
    }}
