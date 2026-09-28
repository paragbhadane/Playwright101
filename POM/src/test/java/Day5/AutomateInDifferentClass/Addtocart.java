package Day5.AutomateInDifferentClass;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Addtocart{
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_page;

    @BeforeMethod
    public void AddToCart() {
        obj_playwright = Playwright.create();

        obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        obj_context = obj_browser.newContext();
        obj_page = obj_context.newPage();
        obj_page.navigate("https://www.saucedemo.com");
    }
    @Test(priority = 3)
    public void TestAddToCartProduct() {
        obj_page.locator("#user-name").fill("standard_user");
        obj_page.locator("#password").fill("secret_sauce");
        obj_page.locator("#login-button").click();

            obj_page.locator("#add-to-cart-sauce-labs-backpack").click();
            obj_page.locator("#add-to-cart-sauce-labs-bike-light").click();
            obj_page.locator("#add-to-cart-sauce-labs-bolt-t-shirt").click();

            assertThat(obj_page.locator(".shopping_cart_badge")).hasText("3");

            obj_page.locator(".shopping_cart_link").click();

         }

    @AfterMethod
    public void teardown(){
        obj_page.close();
        obj_browser.close();
    }
}