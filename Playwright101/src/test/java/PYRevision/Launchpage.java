package PYRevision;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class Launchpage {
    public static void main(String[] args) {

        try(Playwright obj_playwright = Playwright.create())  {

             //launch browser
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

             //create browser context obj
            BrowserContext obj_context = obj_browser.newContext(); //isolated browser context

             //create page object
            Page obj_page = obj_context.newPage();  // New Tab inside a page

            obj_page.navigate("https://saucedemo.com");

            System.out.println("Page title:" + obj_page.title());

           //create locator method
             Locator obj_userName = obj_page.locator("#user-name");
             obj_userName.fill("standard_user");
            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");
            Thread.sleep(2000);
            Locator obj_submitbtn = obj_page.locator("#login-button");
            obj_submitbtn.click();
             Thread.sleep(2000);

            System.out.println(obj_page.url());

            assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");



             obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
