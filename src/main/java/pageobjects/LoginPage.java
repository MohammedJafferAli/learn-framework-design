package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*PageFactory used here to neatly align the web elements
    Element gets the driver using PageFactory initElements #15
    */
    @FindBy(id ="userEmail")
    WebElement inpUserName;

    @FindBy(id="userPassword")
    WebElement inpPassword;

    @FindBy(id="login")
    WebElement btnlogin;

    @FindBy(css = ".ng-trigger-flyInOut")
    WebElement alertInvalidLogin;

    //Action Methods
    public ProductCatalogue loginToApplication(String userName, String password)
    {
        inpUserName.sendKeys(userName);
        inpPassword.sendKeys(password);
        btnlogin.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void launchApp()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage()
    {
        waitForElementToAppear(alertInvalidLogin);
        String errorMessage = alertInvalidLogin.getText();
        return errorMessage;
    }
}
