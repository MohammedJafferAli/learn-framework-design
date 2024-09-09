package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;

    ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    WebElement eleConfirmationMessage;
    @FindBy(css = "i.fa-sign-out")
    WebElement signOut;

    public String getConfirmaitonMessage()
    {
        return eleConfirmationMessage.getText();
    }

    public LoginPage signOut() {
        signOut.click();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }
}
