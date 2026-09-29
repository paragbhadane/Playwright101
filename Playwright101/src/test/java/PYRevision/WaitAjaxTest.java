package PYRevision;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

public class WaitAjaxTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;
    private SoftAssert softAssert;


    @BeforeMethod
    public void setupTest() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void testAjaxQuotesAreDifferent() throws InterruptedException {
        page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/challenge_AjaxPage.html");

        Locator loadQuoteBtn = page.locator("#loadQuoteBtn");
        loadQuoteBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        loadQuoteBtn.click();

        Locator quoteBox1 = page.locator("#quoteBox");
        quoteBox1.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Thread.sleep(2000);

        String quoteText1 = quoteBox1.textContent();
        System.out.println("The First Quote is : " + quoteText1);

        loadQuoteBtn.click();

        Locator quoteBox2 = page.locator("#quoteBox");
        quoteBox2.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Thread.sleep(2000);

        String quoteText2 = quoteBox2.textContent();
        System.out.println("The Second Quote is : " + quoteText2);

        softAssert.assertNotEquals(quoteText1, quoteText1, "Test case FAILED as the quote is the same");
    }

    @AfterMethod
    public void tearDownTest() {
        try {
            softAssert.assertAll();
        } catch (AssertionError failure) {
            if (page != null) {
                String screenshotPath = "screenshots/failure_" + System.currentTimeMillis() + ".png";
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));
                System.out.println("Screenshot captured and the Path is : " + screenshotPath);
            }
            throw failure;
        } finally {
            if (page != null) {
                page.close();
            }
        }
    }
}