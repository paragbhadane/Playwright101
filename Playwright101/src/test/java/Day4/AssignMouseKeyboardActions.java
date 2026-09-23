package Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AssignMouseKeyboardActions {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/challenge_MouseKeyboardActions.html");

            //------Product Laptop
            obj_page.locator("#documentsMenu").hover();
            obj_page.locator("#uploadDocLink").click();
            Thread.sleep(2000);
            // Locator resultLocator = obj_page.locator("#result");
            // assertThat(resultLocator).hasText("You clicked: Laptops");
            //--------------------------------------------

            //--------FileUpload
            String filepath = "C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/fileUpload.html";
            obj_page.setInputFiles("#fileInput", Paths.get(filepath));
            obj_page.locator("#uploadBtn").click();
            Locator Result = obj_page.locator("#result");
            assertThat(Result).containsText("You greedy fellow !!");
            Result.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            System.out.println("Result text: " + Result.textContent());

        }
    }
}
