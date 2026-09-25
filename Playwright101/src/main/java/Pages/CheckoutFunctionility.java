package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutFunctionility {
    private Page page;
    private Locator cartbutton;
    private Locator checkoutButton;
    private Locator firstNameInput;
    private Locator lastNameInput;
    private Locator postalCodeInput;
    private Locator continueButton;


    public void CheckoutFunc(Page page) {
        this.page = page;
        this.cartbutton = page.locator(".shopping_cart_link");
        this.checkoutButton = page.locator("#checkout");
        this.firstNameInput = page.locator("#first-name");
        this.lastNameInput = page.locator("#last-name");
        this.postalCodeInput = page.locator("#postal-code");
        this.continueButton = page.locator("#continue");

    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        cartbutton.click();
        checkoutButton.click();
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        postalCodeInput.fill(postalCode);
        continueButton.click();
    }
}