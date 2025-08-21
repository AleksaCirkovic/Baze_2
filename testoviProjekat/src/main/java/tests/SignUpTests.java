package tests;

import org.junit.jupiter.api.Test;
import pages.LogInPage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpTests extends BaseTest{
    private final String baseUrl = "http://localhost:3000";

    @Test
    public void successfulSignUp() {
        driver.get("baseUrl");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("newUser", "newuser@example.com", "Password1!");

        assertEquals("Registration successful", signUpPage.getResponseMessage());
    }

    @Test
    public void emailAlreadyTaken() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("uniqueUser", "existing@example.com", "Password1!");

        assertEquals("Email already taken", signUpPage.getResponseMessage());
    }

    @Test
    public void usernameAlreadyTaken() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("existingUser", "unique@example.com", "Password1!");

        assertEquals("Username already taken", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordsDoNotMatch() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.registerWithConfirm("userMismatch", "mismatch@example.com", "Password1!", "Password2!");

        assertEquals("Passwords do not match", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordTooShort() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("shortUser", "short@example.com", "Ab1!");

        assertEquals("Password must be at least 8 characters long", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordNoUppercase() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("noUpper", "noupper@example.com", "password1!");

        assertEquals("Password must contain at least one uppercase letter", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordNoLowercase() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("noLower", "nolower@example.com", "PASSWORD1!");

        assertEquals("Password must contain at least one lowercase letter", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordNoNumber() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("noNumber", "nonumber@example.com", "Password!");

        assertEquals("Password must contain at least one number", signUpPage.getResponseMessage());
    }

    @Test
    public void passwordNoSpecialChar() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("noSpecial", "nospecial@example.com", "Password1");

        assertEquals("Password must contain at least one special character", signUpPage.getResponseMessage());
    }

    @Test
    public void termsNotAgreed() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.registerWithoutAgree("userNoAgree", "noagree@example.com", "Password1!");

        assertEquals("You must agree to the terms and conditions", signUpPage.getResponseMessage());
    }

    @Test
    public void invalidEmailFormat() {
        driver.get("http://localhost:3000");
        LogInPage loginPage = new LogInPage(driver);
        loginPage.goToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register("badEmailUser", "invalid-email", "Password1!");

        assertEquals("Invalid email format", signUpPage.getResponseMessage());
    }
}
