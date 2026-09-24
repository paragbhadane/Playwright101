package Day5.SegregationFile;

import Pages.LoginFunctionality;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLogin {
    private Playwright obj_playwright;
    private Browser obj_browser;
    private BrowserContext obj_context;
    private Page obj_page;

    @BeforeMethod
    public void setUp() {
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        obj_context = obj_browser.newContext();
        obj_page = obj_context.newPage();
        obj_page.navigate("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void testLoginPositive() {
        LoginFunctionality loginPage = new LoginFunctionality(obj_page);
        loginPage.performLogin("standard_user", "secret_sauce");
        assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 2)
    public void testLoginNegative() {
        LoginFunctionality loginPage = new LoginFunctionality(obj_page);
        loginPage.performLogin("Incorrect", "Incorrect");
        assertThat(obj_page.locator(".error-button")).isVisible();
    }

    @AfterMethod
    public void teardown() {
        obj_page.close();
        obj_browser.close();
        obj_playwright.close();
    }
}