package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddtoCartFunc {
    private Locator backpackBtn;
    private Locator bikeLightBtn;
    private Locator boltTShirtBtn;
    private Locator shoppingCartBadge;

    public AddtoCartFunc(Page page) {
        this.backpackBtn = page.locator("#add-to-cart-sauce-labs-backpack");
        this.bikeLightBtn = page.locator("#add-to-cart-sauce-labs-bike-light");
        this.boltTShirtBtn = page.locator("#add-to-cart-sauce-labs-bolt-t-shirt");
        this.shoppingCartBadge = page.locator(".shopping_cart_badge");
    }

    public void addProductsToCart() {
        backpackBtn.click();
        bikeLightBtn.click();
        boltTShirtBtn.click();
    }

    public Locator getShoppingCartBadge() {
        return shoppingCartBadge;
    }
}