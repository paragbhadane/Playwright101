package Day2;

import com.microsoft.playwright.*;

import java.util.Arrays;

public class setlocalandTimezone {

    public static void main(String[] args) {

        //try with resources
        try (Playwright obj_playwright = Playwright.create()) { //Playwrite.create() Starts Playwrite driver process

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));


            BrowserContext obj_context = obj_browser.newContext(
                    new Browser.NewContextOptions()
                            //.setLocale("ja-JP")
                            .setTimezoneId("Australia/Sydney")
                            .setViewportSize(null)
            );

            Page obj_page = obj_context.newPage();

            String browserTimezone = (String) obj_page.evaluate(
                    "() => Intl.DateTimeFormat()"
                    + ".resolvedOptions().timeZone"
            );
            System.out.println("Browser Time Zone: " + browserTimezone);



            obj_page.navigate("https://www.wikipedia.org/");
            Thread.sleep(5000);

            Locator obj_langLabel = obj_page.locator("#jsLangLabel");
            String langValue = obj_langLabel.textContent();
            System.out.println("Language Label: " + langValue);



            obj_browser.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}