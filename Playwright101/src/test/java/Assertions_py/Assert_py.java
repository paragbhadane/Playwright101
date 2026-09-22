package Assertions_py;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Assert_py {

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

        page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/welcome.html");
    }

    @Test
    public void testBoxEnable() {
        Locator btn = page.locator("#enterNameBtn");
        btn.click();

        Locator txtbx = page.locator("#nameField");
        assertThat(txtbx).isEnabled();
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
    }
}