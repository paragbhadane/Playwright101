package PYRevision;

import com.microsoft.playwright.*;

public class dragdropheroku {
        public static void main(String[] args) {
            try (Playwright obj_playwright = Playwright.create()) {

                Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

                BrowserContext obj_context = obj_browser.newContext();

                Page obj_page = obj_context.newPage();

                obj_page.navigate("https://the-internet.herokuapp.com/");
                Thread.sleep(2000);
                Locator link = obj_page.locator("//a[@href='/drag_and_drop']");
                link.click();
                Thread.sleep(2000);

                Locator a = obj_page.locator("#column-a");
                Locator b = obj_page.locator("#column-b");

                a.dragTo(b);
                Thread.sleep(2000);

                if(a.textContent().contains("B")){
                    System.out.println("A is moved to right"+ a.innerText() + a.textContent());
                }else{
                    System.out.println("Error");
                }

                obj_browser.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


