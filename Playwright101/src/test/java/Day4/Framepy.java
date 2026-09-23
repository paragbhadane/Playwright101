package Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Framepy {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/iFrameDemo.html");

            System.out.println("Page open in context : " + obj_context.pages().size());
            System.out.println("URL after navigate : " + obj_page.url());


            // 1. By Index

            FrameLocator obj_frame1 = obj_page.frameLocator("iframe").nth(0);

            Locator frame1Btn = obj_frame1.locator("#frame1Btn");
            frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            frame1Btn.click();
            Thread.sleep(2000);

            Locator frame1Result = obj_frame1.locator("#frame1Result");
            assertThat(frame1Result).hasText("Frame 1 button clicked!");

            Thread.sleep(2000);


            // 2. By Name

            FrameLocator obj_frame2 = obj_page.frameLocator("iframe[name='frameByName']");

            Locator frame2Input = obj_frame2.locator("#frame2Input");
            frame2Input.fill("Playwright");
            Thread.sleep(2000);

            assertThat(frame2Input).hasValue("Playwright");


            // 3. By WebElement / CSS Selector

            FrameLocator obj_frame3 = obj_page.frameLocator("#frame3");

            Locator dropdown = obj_frame3.locator("#frame3Dropdown");
            dropdown.selectOption(new SelectOption().setLabel("Two"));
            Thread.sleep(2000);


            assertThat(dropdown.locator("option:checked")).hasText("Two");

            Thread.sleep(2000);

            //Frame 4 Nested Frame
            FrameLocator obj_OuterFrame4 = obj_page.frameLocator("#outerFrame");

            FrameLocator obj_InnerFrame4 = obj_OuterFrame4.frameLocator("#innerFrame");

            Locator frame4Btn = obj_InnerFrame4.locator("#innerFrameBtn");
            frame4Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            frame4Btn.click();
            Thread.sleep(2000);

            Locator frame4Result = obj_InnerFrame4.locator("#innerFrameResult");
            assertThat(frame4Result).hasText("Inner frame button clicked!");

            // 4. Main Page Element

            Locator mainBtn = obj_page.locator("#mainBtn");

            assertThat(mainBtn).hasText("Main Page Button");

            Thread.sleep(2000);

            obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}