package Day4;

import com.microsoft.playwright.*;

public class JSAlertsAssignment {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/javascriptAlerts.html");

            // 1 Simple Alert
            obj_page.onceDialog(dialog -> {
                System.out.println("Alert Text: " + dialog.message());
                dialog.accept();
            });
            obj_page.click("#alertBtn");

            //Assert

//--------------------------------------------------------------
            // 2 Confirm Alert Accept
            obj_page.onceDialog(dialog -> {
                System.out.println("Confirm Accept: " + dialog.message());
                dialog.accept();
            });
            obj_page.click("#confirmBtn");
            Thread.sleep(2000);

            // 2 Confirm Alert Decline
//            obj_page.onceDialog(dialog -> {
//                System.out.println("Confirm decline: " + dialog.message());
//                dialog.dismiss();
//            });
            obj_page.click("#confirmBtn");
            obj_page.onceDialog(Dialog::dismiss);

            Thread.sleep(2000);
//--------------------------------------------------------------
// 4. Prompt Dialog - Accept with input
            obj_page.onceDialog(dialog -> {
                System.out.println("Prompt Accept: " + dialog.message());
                dialog.accept("Zadyaaaaaaaaaa");
            });
            obj_page.click("#promptBtn");

            obj_page.waitForTimeout(1000);

// 5. Prompt Dialog - Dismiss
            obj_page.onceDialog(Dialog::dismiss);
            obj_page.locator("#promptBtn").click();

            obj_page.waitForTimeout(1000);

        }
    }
}
