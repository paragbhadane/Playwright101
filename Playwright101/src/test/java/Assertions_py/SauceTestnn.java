package Assertions_py;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SauceTestnn {
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
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
    }
}