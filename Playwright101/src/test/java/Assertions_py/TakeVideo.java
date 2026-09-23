package Assertions_py;

import com.microsoft.playwright.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TakeVideo {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/")));

        page = context.newPage();

    }

    @Test(priority = 1)
    public void Login() {
        page.navigate("https://www.saucedemo.com/");
        System.out.println(page.title());
        Locator username = page.locator("#user-name");
        username.fill("standard_user");
        Locator password = page.locator("#password");
        password.fill("secret_sauce");
        Locator submitButton = page.locator("#login-button");
        submitButton.click();
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
    @Test(priority = 2)
    public void failedlogin(){
        try {

            page.navigate("https://www.saucedemo.com/");
            System.out.println(page.title());
            Locator username = page.locator("#user-name");
            username.fill("standard");
            Locator password = page.locator("#password");
            password.fill("secret");
            Locator submitButton = page.locator("#login-button");
            submitButton.click();
            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

        } catch (AssertionError e) {

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("faillogin.png"))
                    .setFullPage(true));
            System.out.println("Saved screenshot");
            throw e;
        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        context.close();
        if (result.getStatus() == ITestResult.FAILURE) {
            if (page.video() != null) {
                System.out.println("Test faile " + page.video().path());
            } else {
                page.video().delete();
            }
        }

        browser.close();
        playwright.close();
    }
}