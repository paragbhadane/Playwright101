package Day4;

import com.microsoft.playwright.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileDownload {
    public static void main(String[] args) throws IOException, InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
                            .setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext(
                    new Browser.NewContextOptions().setAcceptDownloads(true)
            );
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/fileDownload.html");

            try {
                Download obj_download = obj_page.waitForDownload(() -> {
                    obj_page.click("#downloadBtn");
                });

                System.out.println("Download Started : Suggested File name: " + obj_download.suggestedFilename());

                Path saveDir = Paths.get("C:/Users/ccst/Downloads");
                Files.createDirectories(saveDir);

                Path savePath = saveDir.resolve(obj_download.suggestedFilename());
                obj_download.saveAs(savePath);

                if (Files.exists(savePath)) {
                    System.out.println("PASS: Downloaded file exists at expected location: " + savePath);
                } else {
                    System.out.println("FAIL: Downloaded file not found at expected location");
                }

                assertThat(Files.exists(savePath)).isTrue();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                obj_browser.close();
            }
        }
    }
}