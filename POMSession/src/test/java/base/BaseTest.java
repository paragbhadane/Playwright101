package base;

import com.microsoft.playwright.*;
import config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void Setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setStorageStatePath(Paths.get(TestConfig.USER_STATE)));
        page = context.newPage();
    }
    @AfterMethod
    public void tearDown(){
        if(context != null) context.close();
    if(browser != null) browser.close();
    if(playwright != null) playwright.close();
    }

}
