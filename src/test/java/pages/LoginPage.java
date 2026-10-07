package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators taken from the Appium Inspector page source (accessibility ids)
    private final By menuButton = AppiumBy.accessibilityId("open menu");
    private final By loginScreen = AppiumBy.accessibilityId("login screen");
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginButton = AppiumBy.accessibilityId("Login button");

    // TODO: NOT verified yet. Open the app menu in Appium Inspector and check the
    // content-desc of the "Log In" menu item. Change this one line if it is different.
    private final By menuItemLogin = AppiumBy.accessibilityId("menu item log in");

    // The error text is a TextView inside the "generic-error-message" container.
    // Assumption: verify this after running the locked-out scenario once.
    private final By errorMessageText = By.xpath(
            "//*[@content-desc='generic-error-message']//android.widget.TextView");

    public void openLoginScreen() {
        click(menuButton);
        click(menuItemLogin);
        waitForVisible(loginScreen);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void tapLogin() {
        hideKeyboardIfShown();
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        tapLogin();
    }

    public boolean isLoginScreenGone() {
        return waitForInvisible(loginScreen);
    }

    public String getErrorMessage() {
        return getText(errorMessageText);
    }
}
