package Day1_Setup;

import com.microsoft.playwright.*;

public class DrivingLicense {
    // Fillings
    static String Fullanme = "Zadyaaaaa";
    static String Address = "Sutarwadiiiii";
    static String Age = "107";
    static String PlaceofBirth = "America";

    // Locator to be used in the code
    static String fullName = "#fullname";
    static String address = "#address";
    static String age = "#age";
    static String placeOfBirth = "#placeofbirth";
    static String rBtnM = "#Male";
    static String rBtnF = "#Female";
    static String clrBY = "input[name='color_yes']";
    static String clrBN = "input[name='color_no']";
    static String sbtBtn = "#myForm button[type='submit']";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            try {
                obj_page.navigate("file:///C:/Users/ccst/Desktop/Playwright/PlaywrightMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

                Thread.sleep(1000);
                obj_page.locator(fullName).fill(Fullanme);
                obj_page.locator(address).fill(Address);
                Thread.sleep(1000);
                obj_page.locator(age).fill(Age);
                obj_page.locator(placeOfBirth).fill(PlaceofBirth);
                Thread.sleep(1000);
                obj_page.locator(rBtnM).check();
                obj_page.locator(clrBN).check();

                //This is new Context to access the new page
                Page obj_newPage = obj_context.waitForPage(() -> {
                    obj_page.locator(sbtBtn).click();
                });
                //Verify the new page title
                String pageTitle = obj_newPage.title();
                if (pageTitle.equals("Welcome")) {
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Failed Login. Actual Title: " + pageTitle);
                }




                obj_newPage.close();
                obj_page.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_browser.close();
            }
        }
    }
}