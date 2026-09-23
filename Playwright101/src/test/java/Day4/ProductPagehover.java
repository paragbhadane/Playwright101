package Day4;

import com.microsoft.playwright.*;

import java.util.concurrent.atomic.AtomicInteger;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductPagehover {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/actionClass_Menu.html");

            //------Product Laptop
            obj_page.locator("#productsMenu").hover();
            obj_page.locator("#laptopsLink").click();
            Thread.sleep(2000);
            obj_page.locator("h2").click();
            Locator resultLocator = obj_page.locator("#result");
            assertThat(resultLocator).hasText("You clicked: Laptops");
           //--------------------------------------------

            //------Services Support
            obj_page.locator("#servicesMenu").hover();
            obj_page.locator("#supportLink").click();
            Thread.sleep(2000);
            obj_page.locator("h2").click();
            Locator resultLocatorSer = obj_page.locator("#result");
            assertThat(resultLocatorSer).hasText("You clicked: Support");
            //--------------------------------------------

            //------Services Training
            obj_page.locator("#servicesMenu").hover();
            obj_page.locator("#trainingLink").click();
            Thread.sleep(2000);
            obj_page.locator("h2").click();
            Locator resultLocatorSerTra = obj_page.locator("#result");
            assertThat(resultLocatorSerTra).hasText("You clicked: Training");
            //--------------------------------------------

            //------About Team
            obj_page.locator("#aboutMenu").hover();
            obj_page.locator("#teamLink").click();
            Thread.sleep(2000);
            obj_page.locator("h2").click();
            Locator resultLocatorAboTeam = obj_page.locator("#result");
            assertThat(resultLocatorAboTeam).hasText("You clicked: Team");
            //--------------------------------------------

            //------About Career
            obj_page.locator("#aboutMenu").hover();
            obj_page.locator("#careersLink").click();
            Thread.sleep(2000);
            obj_page.locator("h2").click();
            Locator resultLocatorAboCarr = obj_page.locator("#result");
            assertThat(resultLocatorAboCarr).hasText("You clicked: Careers");
            //--------------------------------------------

            obj_browser.close();
        }
    }
}