package setup;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;

public class GlobalAuthSetup {

    @BeforeSuite
    public void setupAuthState() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://www.saucedemo.com/");
            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();

            page.waitForURL("**/inventory.html");

            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get("auth/storageState.json")));

            context.close();
            browser.close();
        }
    }
}