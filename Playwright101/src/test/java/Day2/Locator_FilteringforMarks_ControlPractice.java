package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class Locator_FilteringforMarks_ControlPractice {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = browser.newContext();
            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");

            //--------1. getByPlaceholder/TestID/Text----------------

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
            Thread.sleep(2000);

//            Locator ccst = obj_newPage.locator("#module")
//                    .filter(new Locator.FilterOptions()
//                            .setHas(page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("CCST"))));

            Locator module = obj_newPage.locator("#module");
            Locator options = module.locator("option");
            Locator target = options.filter(new Locator.FilterOptions().setHasText("CCST"));

            String value = target.getAttribute("value");
            System.out.println("Extracted Value: " + value);
            module.selectOption(value);
            Thread.sleep(2000);
//------------------------------------------------------------------------------------------------
//            Locator table = obj_newPage.locator("#studentTableBody");
//            Locator row = table.locator("tr");
//            Locator targetrow = row.filter(new Locator.FilterOptions().setHasText("Priya"));
//
//            Locator marksInput = targetrow.locator("input[type='number']");
//            marksInput.fill("85");
//            Thread.sleep(2000);


//--------------------------Ma'am Method -----------------------------------------------
            Locator Rows = obj_newPage.locator("#studentTableBody tr");
            Locator rowsWithMarks = Rows.filter(
                    new Locator.FilterOptions().setHas(obj_newPage.locator("input[type='number']"))
            );
            for (Locator singleRow : rowsWithMarks.all()) {
                Locator markInput = singleRow.locator("input[type='number']");
                markInput.fill("99");
                Thread.sleep(2000);
            }






            browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}





























