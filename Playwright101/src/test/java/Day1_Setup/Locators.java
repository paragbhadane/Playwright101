package Day1_Setup;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Tracing;

import java.nio.file.Paths;

public class Locators {
    // Credentials
    static String username = "standard_user";
    static String password = "secret_sauce";

    // Locators
    static String XpathLoginbutton = "//input[@id='login-button']";
    static String cssLoginButton = "#login-button";
    static String idUsername = "#user-name";
    static String name_user = "[name='user-name']";
    static String PlaceholderUsername = "[placeholder='Username']";
    static String id_password = "#password";

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();

            obj_context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page obj_page = obj_context.newPage();

            try {
                obj_page.navigate("https://www.saucedemo.com");

                obj_page.locator(idUsername).fill(username);
                obj_page.locator(id_password).fill(password);
                obj_page.locator(cssLoginButton).click();

                String pageTitle = obj_page.title();
                if (pageTitle.equals("Swag Labs")) {
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Failed Login");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_context.tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("D:\\trace.zip")));

                obj_browser.close();
            }
        }
    }
}