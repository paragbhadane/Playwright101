package Assertions_py;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TakeVideo {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1280, 720));

        page = context.newPage();
        page.navigate("https://www.saucedemo.com");
    }

    @Test
    public void testLoginSuccessful() {
        Locator obj_userName = page.locator("#user-name");
        obj_userName.fill("standard_user");

        Locator obj_password = page.locator("#password");
        obj_password.fill("secret_sauc");

        Locator obj_submitbtn = page.locator("#login-button");
        obj_submitbtn.click();

        System.out.println("Current URL: " + page.url());


        assertThat(page).hasURL("https://www.saucedemo.com/inventory1.html");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        Video video = page != null ? page.video() : null;

        if (context != null) {
            context.close();
        }

        if (video != null) {
            Path videoPath = video.path();
            if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("Test Failed - Keeping video: " + videoPath);
            } else {
                try {
                    Files.deleteIfExists(videoPath);
                    System.out.println("Test Passed - Video discarded.");
                } catch (Exception e) {
                    System.out.println("Could not delete video: " + e.getMessage());
                }
            }
        }

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}