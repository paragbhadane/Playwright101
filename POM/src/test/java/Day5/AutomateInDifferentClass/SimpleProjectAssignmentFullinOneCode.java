package Day5.AutomateInDifferentClass;

import com.microsoft.playwright.*;

public class SimpleProjectAssignmentFullinOneCode {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            try {
                obj_page.navigate("https://www.saucedemo.com");

                obj_page.locator("#user-name").fill("standard_user");
                obj_page.locator("#password").fill("secret_sauce");
                obj_page.locator("#login-button").click();

                if (obj_page.url().contains("inventory.html")) {
                    System.out.println("VALIDATION PASSED: Login Successful");
                } else {
                    System.out.println("VALIDATION FAILED: Login Failed");
                }

                obj_page.locator("#add-to-cart-sauce-labs-backpack").click();
                obj_page.locator("#add-to-cart-sauce-labs-bike-light").click();
                obj_page.locator("#add-to-cart-sauce-labs-bolt-t-shirt").click();

                String cartBadgeCount = obj_page.locator(".shopping_cart_badge").textContent();
                if (cartBadgeCount.equals("3")) {
                    System.out.println("VALIDATION PASSED: 3 items added to cart badge");
                } else {
                    System.out.println("VALIDATION FAILED: Cart count expected 3 but found " + cartBadgeCount);
                }

                obj_page.locator(".shopping_cart_link").click();

                obj_page.locator("#checkout").click();

                obj_page.locator("#first-name").fill("Zadyaaaaaa");
                obj_page.locator("#last-name").fill("Bhauuuuuuuuuu");
                obj_page.locator("#postal-code").fill("411014");
                obj_page.locator("#continue").click();

                if (obj_page.url().contains("checkout-step-two.html")) {
                    System.out.println("VALIDATION PASSED: Navigated to Checkout Overview page");
                } else {
                    System.out.println("VALIDATION FAILED: Failed to navigate to Checkout Overview");
                }

                obj_page.locator("#finish").click();


                obj_page.locator("#back-to-products").click();
                obj_page.locator("#react-burger-menu-btn").click();
                obj_page.locator("#logout_sidebar_link").click();

                if (obj_page.locator("#login-button").isVisible()) {
                    System.out.println("VALIDATION PASSED: Logged out successfully");
                } else {
                    System.out.println("VALIDATION FAILED: Logout failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_browser.close();
            }
        }
    }
}