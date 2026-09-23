package Day4;

import com.microsoft.playwright.*;

import java.util.List;

public class HerokkuSortAssignment {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");
            Thread.sleep(2000);
            Locator link = obj_page.locator("//a[@href='/tables']");
            link.click();
            Thread.sleep(2000);

            Locator lstName = obj_page.getByText("Last Name").nth(0);
            lstName.click();
            Thread.sleep(2000);

            Locator firstColumnLocator = obj_page.locator("table tbody tr td");

            List<String> actualList = firstColumnLocator.allInnerTexts();
            System.out.println(actualList);


        }
    }
}