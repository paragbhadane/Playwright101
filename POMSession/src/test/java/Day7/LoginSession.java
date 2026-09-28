package Day7;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class LoginSession {
    //##################input data
    static String username = "standard_user";
    static String password = "secret_sauce";

    //################Locator
    static String loginbtn = "#login-button";
    static String idusername = "#user-name";
    static String idpassword = "#password";

    public static void main(String[] args) {

        try(Playwright obj_playwright = Playwright.create())  {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

            BrowserContext obj_context = obj_browser.newContext(); //isolated browser context

            Page obj_page = obj_context.newPage();  // New Tab inside a page

            obj_page.navigate("https://saucedemo.com");



            Locator obj_userName = obj_page.locator(idusername);
            obj_userName.fill(username);
            Locator obj_password = obj_page.locator(idpassword);
            obj_password.fill(password);
            Thread.sleep(2000);
            Locator obj_submitbtn = obj_page.locator(loginbtn);
            obj_submitbtn.click();
            Thread.sleep(2000);
            System.out.println("Page title:" + obj_page.title());

            assertThat(obj_page).hasTitle("Swag Labs");

            obj_context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get("auth/storageState.json"))
            );
            obj_page.close();
            obj_context.close();

            BrowserContext obj_context1 = obj_browser.newContext(
                    new Browser.NewContextOptions()
                            .setStorageStatePath(Paths.get("auth/storageState.json")));
            Page obj_page1 = obj_context1.newPage();
            obj_page1.navigate("https://www.saucedemo.com/inventory.html");
            Thread.sleep(1000);


            obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
