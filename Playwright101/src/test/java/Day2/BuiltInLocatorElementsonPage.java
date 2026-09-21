package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class BuiltInLocatorElementsonPage {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = browser.newContext();
            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");

            //--------1. getByPlaceholder/TestID/Text----------------

            page.getByPlaceholder("Enter username").fill("admin");
            Thread.sleep(2000);
            page.getByTestId("password-input").fill("admin");
            Thread.sleep(2000);


            Page obj_newPage = obj_context.waitForPage(() -> {
                page.getByText("Sign In").click();
            });
            Thread.sleep(2000);

            String pageTitle = obj_newPage.title();
            if (pageTitle.equals("Student Performance Report")) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Failed Login. Actual Title: " + pageTitle);
            }

            Locator modu = obj_newPage.locator("#module");
            //Locator studrow = obj_newPage.locator("#module");
            modu.selectOption("CCST");
            Thread.sleep(2000);

            modu.selectOption(new SelectOption().setLabel("DAI"));
            Thread.sleep(2000);

            modu.selectOption(new SelectOption().setIndex(3));
            Thread.sleep(2000);


            browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}





























