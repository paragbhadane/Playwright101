package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WelcomeWait {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            long start = System.currentTimeMillis();

            // 1. Navigate first
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/welcome.html");

            // 2. Fixed spelling: #message (verify with your HTML file)
            Locator message = page.locator("#message");

            // 3. Wait for the element to appear
            message.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            String label = message.innerText();

            long els = System.currentTimeMillis() - start;

            System.out.println("TEXT: " + label);
            System.out.println("Wait: " + els + "ms");

            browser.close();
        }
    }
}