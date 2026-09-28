package tests;

import Pages.AddtoCartFunc;
import Pages.CheckoutFunctionility;
import Pages.LogoutFunctionality;
import base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLogout extends BaseTest {

    @Test
    public void testLogoutWithoutLogin() {
        page.navigate("https://www.saucedemo.com/inventory.html");

        AddtoCartFunc add = new AddtoCartFunc(page);
        add.addProductsToCart();

        CheckoutFunctionility check = new CheckoutFunctionility();
        check.CheckoutFunc(page);
        check.fillCheckoutInformation("Standard", "User", "411001");

        assertThat(page).hasURL("https://www.saucedemo.com/checkout-complete.html");

        LogoutFunctionality logoutPage = new LogoutFunctionality(page);
        logoutPage.performLogout();

        assertThat(page).hasURL("https://www.saucedemo.com/");
        assertThat(page.locator("#login-button")).isVisible();
    }
}