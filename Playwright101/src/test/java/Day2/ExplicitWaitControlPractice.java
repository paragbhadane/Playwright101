package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ExplicitWaitControlPractice {

    public static void main(String[] args) {


        try (Playwright playwright=Playwright.create()){

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/ControlsPractice.html");

            page.locator("#module").selectOption(new SelectOption().setValue("CCST"));

            Locator rows = page.locator("table tbody tr")
                    .filter(new Locator.FilterOptions().setHas(page.locator("input[type='number']")));

            int rowcount = rows.count();


            for (int i = 0; i < rowcount; i++) {
                Locator rowq = rows.nth(i);

                Locator markInput = rowq.locator("input[type='number']");
                markInput.fill(String.valueOf(90+i));

            }
            rows.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            page.locator("#saveButton").click();




        }
    }
}