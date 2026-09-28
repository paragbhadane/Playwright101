package tests;

import Pages.AddtoCartFunc;
import Pages.CheckoutFunctionility;
import base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestCheckout extends BaseTest {

    @Test
    public void testCheckoutWithoutLogin() {
        page.navigate("https://www.saucedemo.com/inventory.html");

        AddtoCartFunc cartFunc = new AddtoCartFunc(page);
        cartFunc.addProductsToCart();

        CheckoutFunctionility checkoutFunc = new CheckoutFunctionility();
        checkoutFunc.CheckoutFunc(page);
        checkoutFunc.fillCheckoutInformation("Standard", "User", "411001");

        assertThat(page).hasURL("https://www.saucedemo.com/checkout-complete.html");
        assertThat(page.locator(".complete-header")).hasText("Thank you for your order!");
    }
}