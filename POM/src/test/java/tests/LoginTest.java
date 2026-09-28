package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void LoginDone() {
        page.navigate("https://www.saucedemo.com/inventory.html");

        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
}