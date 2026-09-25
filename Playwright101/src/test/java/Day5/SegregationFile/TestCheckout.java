package Day5.SegregationFile;

import Pages.AddtoCartFunc;
import Pages.CheckoutFunctionility;
import Pages.LoginFunctionality;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestCheckout {
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

    @Test(priority = 4)
    public void testCheckoutProduct() {
        LoginFunctionality loginPage = new LoginFunctionality(obj_page);
        loginPage.performLogin("standard_user", "secret_sauce");

        AddtoCartFunc add = new AddtoCartFunc(obj_page);
        add.addProductsToCart();

        CheckoutFunctionility check = new CheckoutFunctionility();
        check.CheckoutFunc(obj_page);
        check.fillCheckoutInformation("Standard", "User", "411001");

        // 4. Verify landing on Checkout: Overview (Step 2)
        assertThat(obj_page).hasURL("https://www.saucedemo.com/checkout-step-two.html");

        // 5. Complete order by clicking Finish
        obj_page.locator("#finish").click();

        // 6. Verify checkout completion
        assertThat(obj_page).hasURL("https://www.saucedemo.com/checkout-complete.html");
        assertThat(obj_page.locator(".complete-header")).hasText("Thank you for your order!");
    }

    @AfterMethod
    public void teardown() {
        if (obj_page != null) obj_page.close();
        if (obj_context != null) obj_context.close();
        if (obj_browser != null) obj_browser.close();
        if (obj_playwright != null) obj_playwright.close();
    }
}