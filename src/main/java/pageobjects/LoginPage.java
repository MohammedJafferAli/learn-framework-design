package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
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

    //Action Methods
    public void loginToApplication(String userName, String password)
    {
        inpUserName.sendKeys(userName);
        inpPassword.sendKeys(password);
        btnlogin.click();
    }

    public void launchApp()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
