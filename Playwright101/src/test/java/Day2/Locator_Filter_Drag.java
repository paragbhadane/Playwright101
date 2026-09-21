package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import javax.xml.transform.SourceLocator;
import java.util.List;

public class Locator_Filter_Drag {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = browser.newContext();
            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/LocatorFiltering.html");


           Locator sourceContainer = page.locator(".container")
                   .filter(new Locator.FilterOptions()
                           .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("TO DO"))));

            Locator targetContainer = page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("DONE"))));

            System.out.println("Source container visible: " + sourceContainer.isVisible());
            System.out.println("Target container visible: " + targetContainer.isVisible());

            Locator item1 = page.locator("#item1");

            System.out.println("Item to drag: " + item1.innerText());

            item1.dragTo(targetContainer);

            page.waitForTimeout(500);

         Locator result = page.locator("#result");
            if (result.textContent().equals("Write Selenium Tests moved to Done")) {
                System.out.println("Element Dragged and Dropped");
            } else {
                System.out.println("Element did not dragged and dropped: ");
            }

            browser.close();
        }
    }
}