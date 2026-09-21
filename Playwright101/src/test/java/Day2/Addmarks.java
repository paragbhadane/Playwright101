package Day2;

import com.microsoft.playwright.*;

public class Addmarks {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = browser.newContext();
            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");
            Thread.sleep(2000);
            page.getByPlaceholder("Enter username").fill("admin");
            Thread.sleep(2000);
            page.getByTestId("password-input").fill("admin");
            Thread.sleep(2000);

            Page obj_newPage = obj_context.waitForPage(() -> {
                page.getByText("Sign In").click();

            });

            Thread.sleep(2000);

            String pageTitle = obj_newPage.title();
            if (pageTitle.equals("Student Performance Report")) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Failed Login. Actual Title: " + pageTitle);
            }

            Locator modu = obj_newPage.locator("#module");
            modu.selectOption("CCST");
            Thread.sleep(2000);

            Locator rows = obj_newPage.locator("table").locator("tbody").locator("tr");

            int rowCount = rows.count();
            System.out.println("Total Rows: " + rowCount);
            Thread.sleep(2000);
            for (int i = 0; i < rowCount; i++) {
                Locator Row = rows.nth(i);
                Locator markInput = Row.locator("input[type='number']");
                markInput.fill(String.valueOf(80 + i));
                Thread.sleep(2000);
            }

            obj_newPage.locator("#saveButton").click();
            Thread.sleep(3000);

            browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}