package tests;

import Pages.AddtoCartFunc;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddToCartWithoutLogin() {
        page.navigate("https://www.saucedemo.com/inventory.html");

        AddtoCartFunc cartFunc = new AddtoCartFunc(page);

        cartFunc.addProductsToCart();

        Assert.assertTrue(page.locator(".shopping_cart_badge").isVisible(), "Item should be added to cart");
    }
}