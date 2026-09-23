package Day4;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class demofileupload {
    public static void main(String[] args) throws InterruptedException {

            try (Playwright obj_playwright = Playwright.create()) {

                Browser obj_browser = obj_playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
                );

                BrowserContext obj_context = obj_browser.newContext();
                Page obj_page = obj_context.newPage();

                obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/fileUpload.html");


                String filepath = "C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/fileUpload.html";

                obj_page.setInputFiles("#fileInput", Paths.get(filepath));
                obj_page.locator("#uploadBtn").click();

                Locator Result = obj_page.locator("#result");

                assertThat(Result).containsText("uploaded successfully!");

                if(Result.textContent().contains("uploaded successfully!")){
                    System.out.println("Upload Success");
                }else {
                    System.out.println("Upload Fail ");
                }



                obj_browser.close();
            }
        }
    }
