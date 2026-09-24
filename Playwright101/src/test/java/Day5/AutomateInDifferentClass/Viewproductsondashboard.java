package Day5.AutomateInDifferentClass;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Viewproductsondashboard {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_page;

    @BeforeMethod
    public void ViewProduct() {
        obj_playwright = Playwright.create();

        obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        obj_context = obj_browser.newContext();
        obj_page = obj_context.newPage();
        obj_page.navigate("https://www.saucedemo.com");
    }
    @Test(priority = 2)
    public void TestViewProductOnDashboard() {
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("secret_sauce");
        obj_page.locator("#login-button").click();
        assertThat(obj_page.locator(".inventory_item_name ").nth(0)).hasText("Sauce Labs Backpack");
    }
    @AfterMethod
    public void tearDown() {
        if (obj_browser != null) {
            obj_browser.close();
        }
        if (obj_playwright != null) {
            obj_playwright.close();
        }
    }
}