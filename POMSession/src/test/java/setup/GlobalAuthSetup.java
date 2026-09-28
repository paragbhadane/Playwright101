package setup;

import com.microsoft.playwright.*;
import config.TestConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GlobalAuthSetup {

    public static void loginAndSaveState(String username, String password, String outputPath) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate(TestConfig.LOGIN_URL);

            page.fill("#user-name", username);
            page.fill("#password", password);
            page.click("#login-button");
            page.waitForURL("**/inventory.html");

            Path path = Paths.get(outputPath);
                Files.createDirectories(path.getParent());


            context.storageState(
                    new BrowserContext.StorageStateOptions().setPath(path)
            );

            context.close();
            browser.close();

        } catch (IOException e) {
            throw new RuntimeException("Login failed for user: " + username, e);
        }
    }
}