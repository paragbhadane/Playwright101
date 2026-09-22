package PYRevision;

import com.microsoft.playwright.*;

public class loginhtml {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create()){
            Browser obj_brow = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext con = obj_brow.newContext();

            Page obj_page = con.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");

            obj_page.locator("#username").fill("admin");
            obj_page.locator("#password").fill("admin");

            obj_page.locator("//*[@id='myForm']/button[1]").click();
            Thread.sleep(2000);

            obj_brow.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
