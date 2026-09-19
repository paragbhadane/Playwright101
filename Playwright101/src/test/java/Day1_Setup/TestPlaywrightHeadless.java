package Day1_Setup;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestPlaywrightHeadless {
    public static void main(String[] args) {

        //try with resources
        try (Playwright obj_playwright = Playwright.create()) {

            //Launch browser
            Browser obj_browser = obj_playwright.chromium().launch();

            //Create browser context obj
            BrowserContext obj_context = obj_browser.newContext();

            //Create Page obj
            Page obj_page = obj_context.newPage();
            ;

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
