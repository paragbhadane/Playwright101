package PYRevision;

import com.microsoft.playwright.*;

public class DropDownHerokuapp {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");
            Thread.sleep(2000);
            Locator link = obj_page.locator("//a[@href='/dropdown']");
            link.click();
            Thread.sleep(2000);
            if(obj_page.url().contains("dropdown")) {
                System.out.println("page has opened");
            }else{
                System.out.println("page has not loaded");
            }
           Locator option = obj_page.locator("#dropdown");
            option.selectOption("2");
            Thread.sleep(2000);

           if(option.inputValue().equals("1")){
               System.out.println("print successfully");
           }else{
               System.out.println("unsuccess");
           }

            obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

