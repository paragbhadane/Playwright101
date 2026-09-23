package Day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class NavigatetoNewPage {
        public static void main(String[] args) {
            try (Playwright obj_playwright = Playwright.create()) {

                Browser browser = obj_playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
                );

                BrowserContext obj_context = browser.newContext();
                Page page = obj_context.newPage();
                page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html");

                //--------1. getByLabel----------------

                page.getByLabel("Username").fill("admin");
                page.getByLabel("Password").fill("admin");

                //----------2 GetByRole-----
//                page.getByRole(AriaRole.BUTTON,
//                        new Page.GetByRoleOptions().setName("Sign In")
//                ).click();

                //------------ 3. getBy Text----------

                String paragraphText = page.getByText("Forgot your password?").textContent();
                System.out.println("Pass: Found paragraph using getByText(). Text " + paragraphText);

                Page obj_newPage = page.waitForPopup(() -> {
                    page.locator("//button[@data-testid='submit-btn']").click();
                });


                String pageTitle = obj_newPage.title();
                if (pageTitle.equals("Student Performance Report")) {
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Failed Login. Actual Title: " + pageTitle);
                }


                browser.close();
            }
        }
    }