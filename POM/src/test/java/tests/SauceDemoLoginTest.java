package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoLoginTest {

    @Test
    public void loginAndVerifySixProducts() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com/");

            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();

            page.waitForURL("**/inventory.html");

            int productCount = page.locator(".inventory_item").count();
            System.out.println("Product count: " + productCount);

            Assert.assertEquals(productCount, 6, "Expected 6 products after login");

            browser.close();
        }
    }
}
