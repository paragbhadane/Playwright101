package tests;

import base.BaseTest;
import config.TestConfig;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardTests extends BaseTest {
    @Test
    public void userIsLoggedInWithoutUiLogin() {
        page.navigate(TestConfig.BASE_URL + "/inventory.html");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void userIsLoggedInWithoutUiLoginToCheckout() {
        page.navigate(TestConfig.BASE_URL + "/cart.html");
        assertThat(page).hasURL("https://www.saucedemo.com/cart.html");
    }

}
