package Day4;

import com.microsoft.playwright.*;

import java.util.concurrent.atomic.AtomicInteger;

public class JSAlertOnDialog {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/javascriptAlerts.html");


            AtomicInteger confirmCount = new AtomicInteger(0);
            AtomicInteger promptCount = new AtomicInteger(0);

            obj_page.onDialog(dialog -> {
                System.out.println("Dialog Type: " + dialog.type() + " , message: " + dialog.message());

                switch (dialog.type()) {
                    case "alert":
                        dialog.accept();
                        break;

                    case "confirm":
                        if (confirmCount.getAndIncrement() == 0) {
                            dialog.accept();
                        } else {
                            dialog.dismiss();
                        }
                        break;

                    case "prmopt":
                        if (promptCount.getAndIncrement() == 0) {
                            dialog.accept("Zadyaaaaaaaaaaaa");
                        } else {
                            dialog.dismiss();
                        }
                        break;

                    default:
                        dialog.dismiss();
                }
            });
            //1. Simple Alert
            obj_page.click("#alertBtn");
            obj_page.waitForTimeout(1000);

            //2. Confirmation dialogue accept
            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(1000);

            //3. Confirmation Dialog (dismiss)
            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(1000);

            //4. Prompt Dialogue accept with text
            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(1000);

            //5. Confirmation Dialog (dismiss)
            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(1000);

            obj_browser.close();
        }
    }
}