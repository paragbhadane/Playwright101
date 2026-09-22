package PYRevision;

import com.microsoft.playwright.*;

public class DrgDrop {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/LocatorFiltering.html");
            Thread.sleep(2000);
            Locator trgcnt = obj_page.locator("#targetContainer");
            Locator item1 = obj_page.locator("#item1");
            Locator item2 = obj_page.locator("#item2");
            Locator item3 = obj_page.locator("#item3");


            item1.dragTo(trgcnt);
            item2.dragTo(trgcnt);
            item3.dragTo(trgcnt);
            Thread.sleep(2000);

         Locator result = obj_page.locator("#result");
         if(result.equals("Write Selenium Tests moved to Done")) {
             System.out.println("result passed");
         }else{
             System.out.println("result failed");
         }

         obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
