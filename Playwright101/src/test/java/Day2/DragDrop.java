package Day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import javax.xml.transform.SourceLocator;
import java.util.List;

public class DragDrop {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = browser.newContext();
            Page page = obj_context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/LocatorFiltering.html");


            Locator src = (Locator) page.locator("#sourceContainer");
            List<Locator> lst = page.locator(".draggable-item").all();
            Locator trg = page.locator("#targetContainer");
            for (int i = 1; i <=lst.size() ; i++) {
                Locator item1 = page.locator(".draggable-item").first();
                item1.dragTo(trg);
                Thread.sleep(3000);
            }

//            Locator item1 = page.locator("#item1");
//            Locator item2 = page.locator("#item2");
//            Locator item3 = page.locator("#item3");
//            Locator trg = page.locator("#targetContainer");
//            Thread.sleep(2000);
//            item1.dragTo(trg);
//            Thread.sleep(2000);
//            item2.dragTo(trg);
//            Thread.sleep(2000);
//            item3.dragTo(trg);
//            Thread.sleep(2000);
            // dont hard code the code use list.size to compare if the starting elements and the container elements are same and show the validateion
            Locator result = page.locator("#result");
            if (result.textContent().equals("Write Selenium Tests moved to Done")) {
                System.out.println("Element Dragged and Dropped");
            } else {
                System.out.println("Element did not dragged and dropped: ");
            }

            browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}