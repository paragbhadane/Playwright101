package Day1_Setup;

import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.Map;

public class GeoLocationAssignment {
    static final String LOGIN_URL ="file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/PlaywriteDrive/login.html";

    public static void main(String[] args) {

        //try with resources
        try (Playwright obj_playwright = Playwright.create()) { //Playwrite.create() Starts Playwrite driver process

            //Launch browser
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));


            //Iphone
            BrowserContext obj_iphone15ProMaxContext = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1")
                    .setViewportSize(430, 932)
                    .setDeviceScaleFactor(3)
                    .setIsMobile(true)
                    .setHasTouch(true)
                    .setGeolocation(48.8566,2.3522)
                    .setPermissions(Arrays.asList("geolocation")));

            //Create Page obj
            Page obj_page = obj_iphone15ProMaxContext.newPage();  // New Tab inside a page

            obj_page.navigate("https://wikipedia.com");
            Thread.sleep(5000);
            System.out.println("Page title:" + obj_page.title());


            Map<String, Object> location = (Map<String, Object>) obj_page.evaluate(
                    "() => new Promise((resolve, reject) => {"
                            + "navigator.geolocation.getCurrentPosition("
                            + "p => resolve({ lat: p.coords.latitude, lng: p.coords.longitude }),"
                            + "e => reject(e.message));})"
            );
            double actualLat = ((double) location.get("lat"));
            double actualLng = ((double) location.get("lng"));

            System.out.println("Latitude : " + actualLat + " ,Longitude: " + actualLng);

            obj_browser.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}