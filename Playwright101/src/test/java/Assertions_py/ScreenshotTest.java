package Assertions_py;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenshotTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://saucedemo.com");
    }

    @Test
    public void testLoginSuccessful() {
        Locator obj_userName = page.locator("#user-name");
        obj_userName.fill("standard_user");

        Locator obj_password = page.locator("#password");
        obj_password.fill("secret_sauce");

        Locator obj_submitbtn = page.locator("#login-button");
        obj_submitbtn.click();

        System.out.println("Current URL: " + page.url());


        try {
            assertThat(page).hasURL("https://www.saucedemo.com/inventory1.html");
        } catch (AssertionError e) {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("page.png")).setFullPage(true));
            System.out.println("ScreenshotSaved " );
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
