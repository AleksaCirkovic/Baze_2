package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends BasePage{

    @FindBy(css = ".header-left")
    private WebElement welcomeMessage;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public String getUsername() {
        String text = welcomeMessage.getText(); // npr. "Dobrodošao, user2 na Google Formu.!"
        return text.replaceAll("Dobrodošao, | na Google Formu\\.!", "").trim();
    }
}
