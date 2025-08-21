package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "input[placeholder='Username']")
    private WebElement usernameInput;

    @FindBy(css = "input[placeholder='Email']")
    private WebElement emailInput;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(css = "input[placeholder='Confirm Password']")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox']")
    private WebElement termsCheckbox;

    @FindBy(css = "button")
    private WebElement registerButton;

    @FindBy(css = "div.login-container > p") // Selektujemo prvi <p> ispod buttona
    private WebElement responseMessage;

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void checkTerms() {
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getResponseMessage() {
        return responseMessage.getText();
    }

    public void register(String username, String email, String password) {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        checkTerms();
        clickRegister();
    }
    public void enterPasswordAndConfirm(String password, String confirmPassword) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void registerWithConfirm(String username, String email, String password, String confirmPassword) {
        enterUsername(username);
        enterEmail(email);
        enterPasswordAndConfirm(password, confirmPassword);
        checkTerms();
        clickRegister();
    }
    public void registerWithoutAgree(String username, String email, String password) {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        // ne pozivamo checkTerms()
        clickRegister();
    }
}
