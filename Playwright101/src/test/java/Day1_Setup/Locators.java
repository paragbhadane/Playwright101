package Day1_Setup;

import com.microsoft.playwright.*;

public class Locators {
    //id pass
    static String username = "standard_user";
    static String password = "secret_sauce";
    //locator
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

            Page obj_page = obj_browser.newPage();

            try {
                obj_page.navigate("https://www.saucedemo.com");
                Thread.sleep(2000);

                obj_page.locator(idUsername).fill(username);
                obj_page.locator(id_password).fill(password);
                obj_page.locator(cssLoginButton).click();
                Thread.sleep(2000);

                String pageTitle = obj_page.title();
                if (pageTitle.equals("Swag Labs")) {
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Failed Login");
                }
                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_browser.close();
            }
        }
    }
}