package Day4;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class assignKeyboardsShortcutcopypaste {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/keyboardShortcuts.html");

            Locator src = obj_page.locator("#sourceText");
            src.click();
            obj_page.keyboard().press("Control+A");
            obj_page.keyboard().press("Control+C");

            Locator trg = obj_page.locator("#targetContainer");
            trg.click();
            obj_page.keyboard().press("Control+V");
            Thread.sleep(2000);

            Locator res = obj_page.locator("#result");
            assertThat(res).containsText("Text copied successfully to Target !");

        }
    }
}


