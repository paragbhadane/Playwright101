package PYRevision;

import com.microsoft.playwright.*;

public class herokuappAutomation {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");

            System.out.println("Page title:" + obj_page.title());

            Locator link = obj_page.locator("//a[@href='/add_remove_elements/']");
            link.click();
            Thread.sleep(2000);
            if(obj_page.url().contains("add_remove_elements/")) {
                System.out.println("New page loads correct");
            }else{
                System.out.println("New page not loaded");
            }

            Locator addele = obj_page.locator("//button[@onclick='addElement()']");
            addele.click();

            Locator del = obj_page.locator(".added-manually");
            if(del.isVisible()) {
                System.out.println("Delete button is visible");
            }else{
                System.out.println("Delete button nahi aaya");
            }
            Thread.sleep(2000);
            del.click();
           Thread.sleep(2000);

            if(del.isVisible()) {
                System.out.println("Delete button is visible");
            }else{
                System.out.println("Delete button is not visible after delete");
            }



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}