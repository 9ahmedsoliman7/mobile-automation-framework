package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    // Created lazily so the driver (started in the @Before hook) is guaranteed to exist
    private LoginPage page() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    @Given("the user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        page().openLoginScreen();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWith(String username, String password) {
        page().login(username, password);
    }

    @Then("the user should leave the login screen")
    public void theUserShouldLeaveTheLoginScreen() {
        Assert.assertTrue(page().isLoginScreenGone(),
                "Login screen is still displayed after a login with valid credentials");
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        String message = page().getErrorMessage();
        System.out.println("Error message shown by the app: " + message);
        Assert.assertFalse(message.isBlank(), "Expected an error message but it was empty");
    }
}