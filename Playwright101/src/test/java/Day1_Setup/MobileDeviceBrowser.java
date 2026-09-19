package Day1_Setup;

import com.microsoft.playwright.*;

public class MobileDeviceBrowser {
    static final String LOGIN_URL ="file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html";

    public static void main(String[] args) {

        //try with resources
        try (Playwright obj_playwright = Playwright.create()) { //Playwrite.create() Starts Playwrite driver process

            //Launch browser
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
//Samsung
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Linux; Android 8.1.0; SM-T837A) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                    .setViewportSize(712, 1138)
                    .setDeviceScaleFactor(2.25)
                    .setIsMobile(true)
                    .setHasTouch(true));

            //Create Page obj
            Page obj_page = obj_context.newPage();  // New Tab inside a page

            //Login
            Thread.sleep(2000);
            obj_page.navigate(LOGIN_URL);
            obj_page.locator("[data-testid='username-input']").fill("validUser");
            obj_page.locator("[data-testid='username-input']").fill("validPassword");
            Thread.sleep(2000);

            Page obj_controlspage = obj_context.waitForPage(() -> {
                obj_page.locator("[data-testid='submit-btn']").click();
                    });
            Thread.sleep(2000);
            obj_controlspage.waitForLoadState();

            // Validate navigation by url
            String url = obj_controlspage.url();
            System.out.println("Navigate to : " + url);
            if(!url.contains("ControlsPractice.html")){
                throw new AssertionError("Navigate failed. Expected ControlsPractice.html but got: " + url);
            }

            //validate page title
            String title = obj_controlspage.title();
            System.out.println("page Title :" + title);

            obj_page.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }}
