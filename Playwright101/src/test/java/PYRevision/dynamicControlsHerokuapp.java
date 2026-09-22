package PYRevision;

import com.microsoft.playwright.*;

public class dynamicControlsHerokuapp {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");

            Locator link = obj_page.locator("//a[@href='/dynamic_controls']");
            link.click();
            Thread.sleep(1000);

            Locator checkbox = obj_page.getByLabel("blah");
            checkbox.check();
            Thread.sleep(1000);

            Locator rmvBtn = obj_page.getByLabel("//button[@onclick=/'swapCheckbox()/']");
            rmvBtn.check();
            Thread.sleep(1000);

            //------------------------Enable/disable----------------




        }
    }
}

