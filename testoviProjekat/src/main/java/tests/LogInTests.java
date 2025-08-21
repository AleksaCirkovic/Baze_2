package tests;

import org.junit.jupiter.api.Test;
import pages.LogInPage;
import pages.UserPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInTests extends BaseTest {
    private final String baseUrl = "http://localhost:3000";

    @Test
    public void testSuccessfulLogin() {
        driver.get(baseUrl);

        LogInPage loginPage = new LogInPage(driver);
        loginPage.login("primer.user@example.com", "StrongP@ss1");

        UserPage userPage = new UserPage(driver);
        String username = userPage.getUsername();

        assertEquals("user2", username, "Trebalo bi da se poklapaju");
    }
    @Test
    public void testLoginWithInvalidPassword() {
        driver.get(baseUrl);

        LogInPage loginPage = new LogInPage(driver);
        loginPage.login("primer.user@example.com", "StrongP@ss1");

        String message = loginPage.getResponseMessage();
        assertEquals("Incorrect password", message, "Trebalo bi da pokaže pgrešna lozinka poruku");
    }
    @Test
    public void testLoginWithInvalidEmail() {
        driver.get(baseUrl);

        LogInPage loginPage = new LogInPage(driver);
        loginPage.login("pogresan@example.com", "StrongP@ss1");

        String message = loginPage.getResponseMessage();
        assertEquals("Email not found", message, "Should show email not found message");
    }
    @Test
    public void testLoginWithEmptyFields() {
        driver.get(baseUrl);

        LogInPage loginPage = new LogInPage(driver);
        loginPage.login("", "");

        String message = loginPage.getResponseMessage();
        assertTrue(message.contains("Missing email") || message.contains("Missing password"),
                "Should show message for missing email or password");
    }
}
