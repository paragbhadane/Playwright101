package Day6;

import com.microsoft.playwright.*;

public class herokuappLoadingAdd {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");

            obj_page.locator("//a[@href='/entry_ad']").click();


            obj_page.onDialog(dialog -> {
                System.out.println("dialog message: " + dialog.message());
                dialog.accept();
            });
            Thread.sleep(5000);
        }
    }
}
