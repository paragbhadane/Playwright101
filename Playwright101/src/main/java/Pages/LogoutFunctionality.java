package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class LogoutFunctionality {
        private final Page page;
        private final Locator menuButton;
        private final Locator logoutLink;

        public LogoutFunctionality(Page page) {
            this.page = page;
            this.menuButton = page.locator("#react-burger-menu-btn");
            this.logoutLink = page.locator("#logout_sidebar_link");
        }

        public void performLogout() {
            menuButton.click();
            logoutLink.click();
        }
    }