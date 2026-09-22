package PYRevision;

import com.microsoft.playwright.*;

public class WaitLoginhtml {

    public static void main(String[] args) throws InterruptedException {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");
            Thread.sleep(2000);

            Locator username = obj_page.locator("#username");
            username.fill("admin");
            Thread.sleep(1000);
            Locator password = obj_page.locator("#password");
            password.fill("admin");
            Thread.sleep(1000);
            Locator submitbtn = obj_page.locator("//button[@data-testid='submit-btn']");

            Page obj_newPage = obj_context.waitForPage(() -> {
                submitbtn.click();
            });
            Thread.sleep(1000);

            if(obj_newPage.title().equals("Student Performance Report")){
                System.out.println("Student Report Page Loaded  " + obj_newPage.title());
            }else{
                System.out.println("Page is not loaded ");
            }

            if(obj_newPage.url().contains("ControlsPractice.html?")){
                System.out.println("Student Report Page Loaded and URL is correct \n" + obj_newPage.url());
            }else{
                System.out.println("Page is not loaded ");
            }


        }
    }
}
